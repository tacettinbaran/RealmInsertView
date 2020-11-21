package com.example.realminsertview;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class PersonInformation extends RealmObject {

   private String username;
    private String pasword;
    private String gender;
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonInformation{" +
                "username='" + username + '\'' +
                ", pasword='" + pasword + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
