package com.example.myapplication;

public class Product {
    String productID;
    String productName;
    String productDescription;
    float productPrice;
    Store store;

    public Product(String id, String name, String description, float price, Store market){
        this.productID = id;
        this.productName = name;
        this.productDescription = description;
        this.productPrice = price;
        this.store = market;
    }

    @Override
    public int hashCode(){
        return productID.hashCode();
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

        Product other = (Product)obj;

        if(productID != other.productID){
            return false;
        }
        if(!productName.equals(other.productName)){
            return false;
        }
        if(!productDescription.equals(other.productDescription)){
            return false;
        }
        if(productPrice != other.productPrice){
            return false;
        }
        return store == other.store;
    }

    protected void changeProductID(String id){
        productID = id;
    }

    protected void changeProductName(String name){
        productName = name;
    }

    protected void changeProductDescription(String description){
        productDescription = description;
    }

    protected void changeProductPrice(float price){
        productPrice = price;
    }

    protected void changeStore(Store market){
        store = market;
    }

    public String getProductID(){
        return this.productID;
    }

    public String getProductName(){
        return this.productName;
    }

    public String getProductDescription(){
        return this.productDescription;
    }

    public float getProductPrice(){
        return this.productPrice;
    }

    public Store getStore(){
        return this.store;
    }


}
