package com.example.ourcompany.entity;

import com.example.ourcompany.Location;

/**
 * Created by Vilagra on 21.10.2016.
 */

public class Person {
    private String nameRu;
    private String nameEn;
    private boolean sex;
    private long idIcon;
    private long idPhoto;

    public Person(String nameRu, String nameEn, boolean sex, long idIcon, long idPhoto) {
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.sex = sex;
        this.idIcon = idIcon;
        this.idPhoto = idPhoto;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameByLocation(Location location){
        switch (location){
            case ENGLISH:
                return getNameEn();
            case RUSSIAN:
                return getNameRu();
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean getSex() {
        return sex;
    }

    public long getIdPhoto() {
        return idPhoto;
    }

    public long getIdIcon() {
        return idIcon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return nameEn != null ? nameEn.equals(person.nameEn) : person.nameEn == null;

    }

    @Override
    public int hashCode() {
        return nameEn != null ? nameEn.hashCode() : 0;
    }
}
