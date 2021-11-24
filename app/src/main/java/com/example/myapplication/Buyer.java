package com.example.myapplication;

import androidx.annotation.NonNull;

public class Buyer extends Account{
    public Buyer(String userid, String username, String password, String firstName, String lastName, String emailAddress){
        super(userid, username, password, firstName, lastName, emailAddress);
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
