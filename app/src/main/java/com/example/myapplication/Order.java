package com.example.myapplication;

public class Order {
    String orderid;
    String storeid;
    String productid;
    String userid;
    int status;
    public Order(String orderid, String storeid, String productid, String userid, int status){
        this.orderid = orderid;
        this.storeid = storeid;
        this.productid = productid;
        this.userid = userid;
        this.status = status;
    }
    public String getOrderid() {
        return orderid;
    }

    public String getStoreid() {
        return storeid;
    }

    public String getProductid() {
        return productid;
    }

    public String getUserid() {
        return userid;
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

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public int hashCode(){
        return productid.hashCode();
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
        if (orderid != otherorder.orderid) {
            return false;
        }
        if (storeid != otherorder.storeid) {
            return false;
        }
        if (productid != otherorder.productid) {
            return false;
        }
        if(userid != otherorder.userid){
            return false;
        }
        return status == otherorder.status;
    }
    protected void changeStatus(int newstatus){
        status = newstatus;
    }

}
