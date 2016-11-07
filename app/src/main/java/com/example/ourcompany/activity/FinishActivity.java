package com.example.ourcompany.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.ourcompany.Location;
import com.example.ourcompany.LogicOfGame;
import com.example.ourcompany.MyApplication;
import com.example.ourcompany.R;
import com.example.ourcompany.StoreOfQuestions;
import com.example.ourcompany.entity.Person;

import java.util.Locale;

public class FinishActivity extends AppCompatActivity {
    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        myApplication = (MyApplication) getApplication();
        TextView result = (TextView) findViewById(R.id.result);
        String res = myApplication.getLogicOfGame().getRusult();
        result.setText(res);
        TextView offer = (TextView) findViewById(R.id.offerAnother);
        Location location = myApplication.getStoreOfPersons().getLoc();
        if(location==Location.RUSSIAN) {
            offer.setText(getString(R.string.offer)+" "+getString(R.string.englishVersion));
        }
        else if(location==Location.ENGLISH) {
            offer.setText(getString(R.string.offer)+" "+getString(R.string.russianVersion));
        }

    }

    public void tryAnotherVersion(View view){
        Location location = myApplication.getStoreOfPersons().getLoc();
        if(location==Location.RUSSIAN) {
            invokeAnotherVersion(Location.ENGLISH);
        }
        else if(location==Location.ENGLISH) {
            invokeAnotherVersion(Location.RUSSIAN);
        }

    }

    void invokeAnotherVersion(Location location){
        myApplication.setStoreOfQuestions(new StoreOfQuestions(this,location));
        Person player = myApplication.getLogicOfGame().getPlayer();
        myApplication.setLogicOfGame(new LogicOfGame(player,this,location));
        myApplication.getStoreOfPersons().setLoc(location);
        Locale locale=location.getLocale();
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getResources().updateConfiguration(configuration,null);
        Intent intent = new Intent(this,ActivityOfGame.class);
        startActivity(intent);
        finish();
    }
}
