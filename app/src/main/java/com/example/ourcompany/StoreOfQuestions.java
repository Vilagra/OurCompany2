package com.example.ourcompany;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;

import com.example.ourcompany.entity.Person;
import com.example.ourcompany.entity.Question;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vilagra on 12.10.2016.
 */

public class StoreOfQuestions {

    private int numberOfQuestion;
    List<Question> listofQuestions;
    Iterator<Question> iterator;


    public StoreOfQuestions(Activity act,Location location) {
        extract(act,location);
        iterator = listofQuestions.iterator();
    }

    void extract(Activity activity,Location location) {
        listofQuestions=new ArrayList<>();
        MyApplication myApplication = (MyApplication) activity.getApplication();
        BufferedReader bufferedReader = null;
        try {
            if (location == Location.RUSSIAN) {
                bufferedReader = new BufferedReader(new InputStreamReader(activity.getAssets().open("Question_ru.txt"), "CP1251"));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(activity.getAssets().open("Question_en.txt")));
            }
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] arr = value.split("\\|");
                String question = arr[0];
                Person answer = myApplication.getStoreOfPersons().getPersonByName(arr[1]);
                String description="";
                if(arr.length>2) {
                    description = arr[2];
                }
                Question question1 = new Question(question, answer, description);
                listofQuestions.add(question1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public int getNumberOfQuestion() {
        return ++numberOfQuestion;
    }

    public Question getNextQuestion() {
        return iterator.next();

    }

    public boolean hasNext() {
        return iterator.hasNext();
    }


}
