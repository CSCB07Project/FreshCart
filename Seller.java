package com.example.myapplication;

import androidx.annotation.NonNull;

public class Seller extends Account{
    Store store;
    public Seller(int userid, String username, String password, String firstName, String lastName, String emailAddress) {
        super(userid, username, password, firstName, lastName, emailAddress);
        store = new Store();
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
