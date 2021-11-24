package com.example.myapplication;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Account{
    HashMap<Product,Integer> cart;
    ArrayList<Order> buyerOrders;
    public Buyer(String userid, String username, String password, String firstName, String lastName, String emailAddress){
        super(userid, username, password, firstName, lastName, emailAddress);
        cart = new HashMap<Product, Integer>();
        buyerOrders = new ArrayList<Order>();
    }



    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}