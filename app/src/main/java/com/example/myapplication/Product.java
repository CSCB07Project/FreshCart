package com.example.myapplication;


public class Product {
    String productID;
    String productName;
    String productDescription;
    String productImageUrl;
    float productPrice;
    String store;

    public Product(){}
    public Product(String id, String name, String description, float price, String market, String url){
        this.productID = id;
        this.productName = name;
        this.productDescription = description;
        this.productPrice = price;
        this.store = market;
        this.productImageUrl = url;
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
        if(productImageUrl != other.productImageUrl){
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

    protected void changeStore(String market){
        store = market;
    }

    protected void changeProductImageUrl(String url){
        productImageUrl = url;
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

    public String getStore(){
        return this.store;
    }

    public String getProductImageUrl(){ return this.productImageUrl; }

}
