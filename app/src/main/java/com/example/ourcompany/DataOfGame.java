package com.example.ourcompany;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Vilagra on 12.10.2016.
 */

public class DataOfGame {

    private String languge;
    private int numberOfQuestion;
    private String player;
    private Map<String, String> nameQuestion= new HashMap<>();
    Iterator<Map.Entry<String,String>> iterator;

    public DataOfGame(String languge, Activity act) {
        this.languge=languge;
        try {
            extract(act);
        } catch (IOException e) {
            e.printStackTrace();
        }
        iterator=nameQuestion.entrySet().iterator();
    }

    public int getNumberOfQuestion() {
        return ++numberOfQuestion;
    }


    public String getLanguge() {
        return languge;
    }

    public String getPlayer() {
        return player;
    }

    void extract(Activity activity) throws IOException {
        int index=-1;
        if (languge.equals("English")) {
            index = 2;
        }
        if (languge.equals("Русский")) {
            index = 1;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(activity.getAssets().open("for game.txt"),"CP1251"));
        try {
            String value;
            while ((value=bufferedReader.readLine())!=null){
                String[] arr = value.split("   ");
                if(!arr[index].equals("нет")) {
                    nameQuestion.put(arr[0], arr[index]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            bufferedReader.close();
        }
    }
    public Map.Entry<String,String> getNextQuestion(){
            return iterator.next();

    }
    public boolean hasNext(){
        return iterator.hasNext();
    }

    public List<String> getRandomValues(String correctValue){
        List<String> randomValues = new ArrayList<>();
        List<String> allNames = new ArrayList(nameQuestion.keySet());
        allNames.remove(correctValue);
        randomValues.add(correctValue);
        Collections.shuffle(allNames);
        for (int i = 0; i < 3; i++) {
            randomValues.add(allNames.get(i));
        }
        Collections.shuffle(randomValues);
        return randomValues;
    }
}
