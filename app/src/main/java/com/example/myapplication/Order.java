package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    String orderid;
    String storeid;
    String store_name;
    float total_price;
    HashMap<String, Integer> productids = new HashMap<String, Integer>(); //(Productid: Quantity)
    String userid;
    int status;
    public Order(){};
    public Order(String orderid, String storeid, String userid, int status, float total_price, String store_name){
        this.orderid = orderid;
        this.storeid = storeid;
        this.store_name = store_name;
        this.userid = userid;
        this.status = status;
        //Add empty order object
        productids.put("-1", -1);
    }
    public String getOrderid() {
        return orderid;
    }

    public String getStoreid() {
        return storeid;
    }

    public void addProduct(String id, int quantity){
        productids.remove("-1");//remove null placeholder
        productids.put(id, quantity);
    }

    public String getUserid() {
        return userid;
    }

    public String getStore_name(){
        return this.store_name;
    }

    public float getTotal_price(){
        return this.total_price;
    }

    public void setTotal_price(float price){
        this.total_price = price;
    }

    public void setStore_name(String name){
        this.store_name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public void removeProductId(String id){
        productids.remove(id);
    }

    public HashMap<String, Integer> getProductIds(){
        return this.productids;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public int hashCode(){
        return orderid.hashCode();
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Order otherorder = (Order) obj;
        if (orderid == otherorder.orderid) {
            return true;
        }
        return false;
    }
    protected void changeStatus(int newstatus){
        status = newstatus;
    }

}
