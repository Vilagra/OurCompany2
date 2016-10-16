package com.example.ourcompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        TextView result = (TextView) findViewById(R.id.result);
        String res = this.getString(R.string.Result)+StoreForStatic.logicOfGame.getRusult();
        result.setText(res);
        TextView offer = (TextView) findViewById(R.id.offerAnother);
        String language = StoreForStatic.dataOfGame.getLanguge();
        if(language.equals("Русский")) {
            offer.setText(this.getString(R.string.Answer)+" английскую версию?");
        }
        if(language.equals("English")) {
            offer.setText(this.getString(R.string.Answer)+" русскую версию?");
        }

    }

    public void tryAnotherVersion(View view){
        String language = StoreForStatic.dataOfGame.getLanguge();
        if(language.equals("Русский")) {
            invokeAnotherVersion("English");
        }
        if(language.equals("English")) {
            invokeAnotherVersion("Русский");
        }
    }

    void invokeAnotherVersion(String s){
        StoreForStatic.dataOfGame=new DataOfGame(s,this);
        StoreForStatic.logicOfGame= new LogicOfGame();
        Intent intent = new Intent(this,ActivityOfGame.class);
        startActivity(intent);
    }
}
