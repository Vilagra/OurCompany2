package com.example.ourcompany.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ourcompany.Location;
import com.example.ourcompany.LogicOfGame;
import com.example.ourcompany.MyApplication;
import com.example.ourcompany.StoreOfQuestions;
import com.example.ourcompany.fragments.ChoiceNameFragment;
import com.example.ourcompany.fragments.LanguageFragment;
import com.example.ourcompany.R;
import com.example.ourcompany.StoreOfPersons;

public class MainActivity extends AppCompatActivity implements ChoiceNameFragment.NameFrafmentListener,LanguageFragment.LanguageFragmentListener {
    MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myApplication = (MyApplication) getApplication();
    }


    @Override
    public void languageItemClicked(View v) {
        ChoiceNameFragment choiceNameFragment = new ChoiceNameFragment();
        String s = ((TextView)v).getText().toString();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_names_container,choiceNameFragment);
        choiceNameFragment.setLanguage(s);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    @Override
    public void nameItemClicked(View v) {
        String s = ((TextView)v).getText().toString();
        Location location = myApplication.getStoreOfPersons().getLoc();
        myApplication.setStoreOfQuestions(new StoreOfQuestions(this,location));
        myApplication.setLogicOfGame(new LogicOfGame(myApplication.getStoreOfPersons().getPersonByName(s),this,location));
        Intent intent = new Intent(this,ActivityOfGame.class);
        startActivity(intent);

    }
}
