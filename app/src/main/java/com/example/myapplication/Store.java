package com.example.myapplication;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    int storeID;
    String storeName;
    String storeDescription;
    String storeBannerUrl;

    HashMap<Integer, Order> storeOrders = new HashMap<>();
    HashMap<Integer,Product> products = new HashMap<>();

    public Store(int id, String name, String description, String url){
        this.storeID = id;
        this.storeDescription = description;
        this.storeName = name;
        this.storeBannerUrl = url;
    }

    public void addProduct(Product p){
        products.put(p.productID, p);
    }

    public void removeProduct(Product p){
        products.remove(p.productID);
    }

    public void addOrder(Order o){
        storeOrders.put(o.orderid, o);
    }

    public void removeOrder(Order o){
        storeOrders.remove(o.orderid);
    }

    public int getStoreID(){
        return this.storeID;
    }
    public String getStoreName() {return this.storeName; }
    public String getStoreDescription(){
        return this.storeDescription;
    }

    public String getStoreBannerUrl(){
        return this.storeBannerUrl;
    }

    public void setStoreID(int newId){
        this.storeID = newId;
    }
    public void setStoreName(String name) { this.storeName = name; }
    public void setStoreDescription(String newDesc){
        this.storeDescription = newDesc;
    }

    public void setStoreBannerUrl(String newUrl){
        this.storeBannerUrl = newUrl;
    }


}






