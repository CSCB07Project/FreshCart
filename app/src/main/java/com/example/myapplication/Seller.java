package com.example.myapplication;

import androidx.annotation.NonNull;

public class Seller extends Account{
    Store store;
    public Seller(String userid, String username, String password, String firstName, String lastName, String emailAddress, Store store) {
        super(userid, username, password, firstName, lastName, emailAddress, 0);
        this.store = store;
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
