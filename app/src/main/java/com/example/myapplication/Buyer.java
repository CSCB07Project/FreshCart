package com.example.myapplication;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Account{

    HashMap<String,Integer> cart;
    ArrayList<String> buyerOrders;
    public Buyer(){}
    public Buyer(String userid, String username, String firstName, String lastName, String emailAddress){
        super(userid, username, firstName, lastName, emailAddress, 1);
        cart = new HashMap<String, Integer>();
        buyerOrders = new ArrayList<String>();

        //Required o/w firebase will not add fields during account generation.
        cart.put("-1", -1);
        buyerOrders.add("-1");
    }



    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public HashMap<String,Integer> getCart() {
        return cart;
    }

    public ArrayList<String> getBuyerOrders() {
        return buyerOrders;
    }


}