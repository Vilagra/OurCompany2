package com.example.ourcompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityOfGame extends AppCompatActivity {
    boolean isPushed = true;
    ArrayList<String> valueOfButtons;
    String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_of_game);
        if (savedInstanceState != null) {
            isPushed = savedInstanceState.getBoolean("pushed");
        }
        if (isPushed) {
            isPushed = false;
            Map.Entry<String, String> entry = StoreForStatic.dataOfGame.getNextQuestion();
            StoreForStatic.logicOfGame.setCurentCorect(entry.getKey());
            valueOfButtons = new ArrayList<>(StoreForStatic.dataOfGame.getRandomValues(entry.getKey()));
            int numberOfQuestion=StoreForStatic.dataOfGame.getNumberOfQuestion();
            question = numberOfQuestion+". "+entry.getValue();

        } else {
            question = savedInstanceState.getString("question");
            valueOfButtons = savedInstanceState.getStringArrayList("buttons");
        }
        setButtonsAndQuestion(question, valueOfButtons);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("pushed", isPushed);
        outState.putStringArrayList("buttons", valueOfButtons);
        outState.putString("question", question);
    }

    public void giveAnswer(View view) {
        Button b = (Button) view;
        String s = String.valueOf(b.getText());
        String check = StoreForStatic.logicOfGame.checkAnswer(s);
        Intent intent = new Intent(this, CheckActivity.class);
        intent.putExtra(CheckActivity.CHECKER, check);
        startActivity(intent);

    }

    private void setButtonsAndQuestion(String question, List<String> valueOfButtons) {
        ((TextView) findViewById(R.id.question)).setText(question);
        ((Button) findViewById(R.id.A)).setText(valueOfButtons.get(0));
        ((Button) findViewById(R.id.B)).setText(valueOfButtons.get(1));
        ((Button) findViewById(R.id.C)).setText(valueOfButtons.get(2));
        ((Button) findViewById(R.id.D)).setText(valueOfButtons.get(3));
    }


}
