package com.example.ourcompany;

import android.annotation.SuppressLint;

import java.util.Locale;

/**
 * Created by Vilagra on 02.11.2016.
 */

public enum Location {
    ENGLISH(new Locale("en")), RUSSIAN(new Locale("ru","RU"));

    Locale locale;

    public Locale getLocale() {
        return locale;
    }

    Location(Locale locale) {
        this.locale = locale;
    }

    public String getLanguage(){
        return locale.getDisplayLanguage();
    }

    public static Location getLocationByLanguage(String language, Locale defaultLocale){
        for (Location location : Location.values()) {
            if (location.getLocale().getDisplayLanguage(defaultLocale).equals(language)){
                return location;
            }
        }
        return null;
    }

    public static String[] getArrayLanguages(){
        String[] arr = new String[Location.values().length];
        int index=0;
        for (Location location : Location.values()) {
            arr[index++]=location.getLanguage();
        }
        return arr;
    }


}
