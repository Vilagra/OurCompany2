package com.example.ourcompany;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void start(View view){
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        String str = String.valueOf(spin.getSelectedItem());
        Intent intent = new Intent(this,ActivityOfGame.class);
        StoreForStatic.dataOfGame = new DataOfGame(str,this);
        StoreForStatic.logicOfGame= new LogicOfGame();
        startActivity(intent);

    }
}
