package com.example.ourcompany.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ourcompany.MyApplication;
import com.example.ourcompany.R;
import com.example.ourcompany.StoreOfPersons;
import com.example.ourcompany.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class ActivityOfGame extends AppCompatActivity {
    List<String> valueOfButtons;
    String stringQuestion;
    MyApplication myApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_of_game);
        myApplication = (MyApplication) getApplication();
        if (savedInstanceState != null) {
            stringQuestion = savedInstanceState.getString("question");
            valueOfButtons = savedInstanceState.getStringArrayList("buttons");
        } else {
            Question question = myApplication.getStoreOfQuestions().getNextQuestion();
            int numberOfQuestion = myApplication.getStoreOfQuestions().getNumberOfQuestion();
            myApplication.getLogicOfGame().setQuestion(question);
            stringQuestion = numberOfQuestion+". "+question.getQuestion();
            valueOfButtons = myApplication.getStoreOfPersons().getRandomNameOfPerson(question.getAnswer());
        }
        setButtonsAndQuestion(stringQuestion, valueOfButtons);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("buttons", (ArrayList<String>) valueOfButtons);
        outState.putString("question", stringQuestion);
    }

    public void giveAnswer(View view) {
        Button b = (Button) view;
        String answer = String.valueOf(b.getText());
        Intent intent = new Intent(this, CheckActivity.class);
        intent.putExtra(CheckActivity.ANSWER, answer);
        startActivity(intent);
        this.finish();

    }

    private void setButtonsAndQuestion(String question, List<String> valueOfButtons) {
        StoreOfPersons storeOfPersons = myApplication.getStoreOfPersons();
        ((TextView) findViewById(R.id.question)).setText(question);
        Button buttonA = ((Button) findViewById(R.id.A));
        buttonA.setText(valueOfButtons.get(0));
        buttonA.setCompoundDrawablesWithIntrinsicBounds((int) storeOfPersons.getPersonByName(valueOfButtons.get(0)).getIdIcon(), 0, 0, 0);
        Button buttonB = ((Button) findViewById(R.id.B));
        buttonB.setText(valueOfButtons.get(1));
        buttonB.setCompoundDrawablesWithIntrinsicBounds((int) storeOfPersons.getPersonByName(valueOfButtons.get(1)).getIdIcon(), 0, 0, 0);
        Button buttonC = ((Button) findViewById(R.id.C));
        buttonC.setText(valueOfButtons.get(2));
        buttonC.setCompoundDrawablesWithIntrinsicBounds((int) storeOfPersons.getPersonByName(valueOfButtons.get(2)).getIdIcon(), 0, 0, 0);
        Button buttonD = ((Button) findViewById(R.id.D));
        buttonD.setText(valueOfButtons.get(3));
        buttonD.setCompoundDrawablesWithIntrinsicBounds((int) storeOfPersons.getPersonByName(valueOfButtons.get(3)).getIdIcon(), 0, 0, 0);

    }


}
