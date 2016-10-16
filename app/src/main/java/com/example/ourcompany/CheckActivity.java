package com.example.ourcompany;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CheckActivity extends AppCompatActivity {
    public static final String CHECKER = "checkAnswer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Intent intent = getIntent();
        System.out.println(intent);
        String res = intent.getStringExtra(CHECKER);
        ((TextView)findViewById(R.id.check)).setText(res);
    }

    public void continueGame(View view){
        if (StoreForStatic.dataOfGame.hasNext()) {
            Intent intent = new Intent(this, ActivityOfGame.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this,FinishActivity.class);
            startActivity(intent);
        }


    }
}
