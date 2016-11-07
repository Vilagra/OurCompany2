package com.example.ourcompany;

import android.app.Application;

import com.example.ourcompany.entity.Person;

/**
 * Created by Vilagra on 02.11.2016.
 */

public class MyApplication extends Application{
    private StoreOfPersons storeOfPersons;
    private StoreOfQuestions storeOfQuestions;
    private LogicOfGame logicOfGame;

    public LogicOfGame getLogicOfGame() {
        return logicOfGame;
    }

    public void setLogicOfGame(LogicOfGame logicOfGame) {
        this.logicOfGame = logicOfGame;
    }

    public StoreOfPersons getStoreOfPersons() {
        return storeOfPersons;
    }

    public void setStoreOfPersons(StoreOfPersons storeOfPersons) {
        this.storeOfPersons = storeOfPersons;
    }
    public StoreOfQuestions getStoreOfQuestions() {
        return storeOfQuestions;
    }

    public void setStoreOfQuestions(StoreOfQuestions storeOfQuestions) {
        this.storeOfQuestions = storeOfQuestions;
    }
}
