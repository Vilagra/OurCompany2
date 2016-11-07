package com.example.ourcompany;

import android.app.Activity;

import com.example.ourcompany.entity.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Vilagra on 21.10.2016.
 */

public class StoreOfPersons {
    private List<Person> listAllPersons = new ArrayList<>();
    Location loc;

    public StoreOfPersons(Activity act, Location loc) {
        this.loc=loc;
        extract(act);

    }

    public List<Person> getListAllPersons() {
        return listAllPersons;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    void extract(Activity activity) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(activity.getAssets().open("Persons.txt"), "UTF-8"));
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] arr = value.split("\\|");
                String naneRu = arr[0];
                String nameEn = arr[1];
                boolean sex = new Boolean(arr[2]);
                String nameOfDrawable=arr[1].toLowerCase();
                if(nameOfDrawable.split(" ").length>1){
                    String[] arr2 = nameOfDrawable.split(" ");
                    nameOfDrawable = arr2[0]+arr2[1];
                }
                int idIcon = activity.getResources().getIdentifier(nameOfDrawable, "drawable", "com.example.ourcompany");
                int idPhoto = activity.getResources().getIdentifier(nameOfDrawable+"2", "drawable", "com.example.ourcompany");
                listAllPersons.add(new Person(naneRu, nameEn, sex, idIcon, idPhoto));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Person getPersonByName(String name){
        for (Person person : listAllPersons) {
            if(person.getNameEn().equals(name)||person.getNameRu().equals(name)){
                return person;
            }
        }
        throw new IllegalArgumentException();
    }


    public List<Person> getListPersonBySex(boolean sex ){
        List<Person> listMan=new ArrayList<>();
        for (Person person : listAllPersons) {
            if(person.getSex()==sex){
                listMan.add(person);
            }
        }
        return listMan;
    }

    public List<String> getRandomNameOfPerson(Person correctValue){
        List<String> randomValues = new ArrayList<>();
        List<Person> allPersonBySex= getListPersonBySex(correctValue.getSex());
        allPersonBySex.remove(correctValue);
        randomValues.add(correctValue.getNameByLocation(loc));
        Collections.shuffle(allPersonBySex);
        for (int i = 0; i < 3; i++) {
            randomValues.add(allPersonBySex.get(i).getNameByLocation(loc));
        }
        Collections.shuffle(randomValues);
        return randomValues;
    }

}
