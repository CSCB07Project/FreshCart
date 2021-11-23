package com.example.myapplication;

public class Order {
    int orderid;
    int storeid;
    int productid;
    int userid;
    int status;
    public Order(int orderid, int storeid, int productid, int userid, int status){
        this.orderid = orderid;
        this.storeid = storeid;
        this.productid = productid;
        this.userid = userid;
        this.status = status;
    }
    @Override
    public int hashCode(){
        return productid;
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
