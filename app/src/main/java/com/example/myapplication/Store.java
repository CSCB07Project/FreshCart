package com.example.myapplication;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    String storeID;
    int storeContact;
    String storeName;
    String storeDescription;
    String storeBannerUrl;
    String storeAddress;
    String storeCountry;
    String storeProvince;
    String storeCity;
    String storePostal;

    //HashMap<Integer, Order> storeOrders = new HashMap<>();
    ArrayList<String> storeOrders = new ArrayList<String>();
    //HashMap<Integer,Product> products = new HashMap<>();
    ArrayList<String> products = new ArrayList<String>();
    HashMap<String,String> openingHours = new HashMap<>();

    public Store(){

    }
    public Store(String id, String name, String description, String bannerurl, String address, String country, String province, String city, String postal){
        this.storeID = id;
        this.storeName = name;
        this.storeDescription = description;
        this.storeBannerUrl = bannerurl;
        this.storeAddress = address;
        this.storeCountry = country;
        this.storeProvince = province;
        this.storeCity = city;
        this.storePostal = postal;
    }

    public void addProduct(Product p){
        products.add(p.productID);
    }

    public void removeProduct(Product p){
        products.remove(p.productID);
    }

    public void addOrder(Order o){
        storeOrders.add(o.orderid);
    }

    public void removeOrder(Order o){
        storeOrders.remove(o.orderid);
    }

    public void addHours(String date, String hours) { openingHours.put(date, hours); }

    public void removeHours(String date) { openingHours.remove(date); }



    public String getStoreID(){
        return this.storeID;
    }
    public int getStoreContact(){ return this.storeContact; }
    public String getStoreName() {return this.storeName; }
    public String getStoreDescription(){
        return this.storeDescription;
    }
    public String getStoreBannerUrl(){
        return this.storeBannerUrl;
    }
    public String getStoreAddress() { return this.storeAddress; }
    public String getStoreCountry() { return this.storeCountry; }
    public String getStoreProvince() { return this.storeProvince; }
    public String getStoreCity() { return this.storeCity; }
    public String getStorePostal() { return this.storePostal; }

    public void setStoreID(String newId){
        this.storeID = newId;
    }
    public void setStoreContact(int contactNum) { this.storeContact = contactNum; }
    public void setStoreName(String name) { this.storeName = name; }
    public void setStoreDescription(String newDesc){
        this.storeDescription = newDesc;
    }
    public void setStoreBannerUrl(String newUrl){
        this.storeBannerUrl = newUrl;
    }
    public void setStoreAddress(String address){
        this.storeAddress = address;
    }
    public void setStoreCountry(String country){
        this.storeCountry = country;
    }
    public void setStoreProvince(String province){
        this.storeProvince = province;
    }
    public void setStoreCity(String city){
        this.storeCity = city;
    }
    public void setStorePostal(String postal){
        this.storePostal = postal;
    }
}






