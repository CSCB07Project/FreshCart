package com.example.myapplication;

import androidx.annotation.NonNull;

public class Seller extends Account{
    String storeID;
    public Seller(String userid, String username,String firstName, String lastName, String emailAddress, String storeID) {
        super(userid, username,firstName, lastName, emailAddress, 0);
        this.storeID = storeID;
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String getStoreID() {
        return storeID;
    }

}
