package com.example.ourcompany.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ourcompany.MyApplication;
import com.example.ourcompany.R;

public class CheckActivity extends AppCompatActivity {
    public static final String ANSWER = "Answer";
    MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        myApplication= (MyApplication) getApplication();
        Intent intent = getIntent();
        String answer = intent.getStringExtra(ANSWER);
        String res = myApplication.getLogicOfGame().checkAnswer(answer);
        ((TextView)findViewById(R.id.check)).setText(res);
        String description = myApplication.getLogicOfGame().getQuestion().getDescription();
        ((TextView)findViewById(R.id.description)).setText(description);
        ((ImageView)findViewById(R.id.photo)).setImageResource((int) myApplication.getLogicOfGame().getQuestion().getAnswer().getIdPhoto());
    }

    public void continueGame(View view){
        if (myApplication.getStoreOfQuestions().hasNext()) {
            Intent intent = new Intent(this, ActivityOfGame.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this,FinishActivity.class);
            startActivity(intent);
        }
        finish();


    }
}
