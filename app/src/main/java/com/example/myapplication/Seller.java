package com.example.myapplication;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Seller extends Account{
    String storeID;
    ArrayList<String> orders = new ArrayList<String>();
    public Seller(String userid, String username,String firstName, String lastName, String emailAddress, String storeID) {
        super(userid, username,firstName, lastName, emailAddress, 0);
        this.storeID = storeID;
        orders.add("-1");
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String getStoreID() {
        return storeID;
    }

    public void addOrder(String id){
        orders.remove("-1");
        orders.add(id);
    }

    public void removeOrder(String id){
        orders.remove(id);
    }

}
