package com.example.myapplication.Dashboard_Seller.models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.Product;
import com.example.myapplication.Store;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductReaderWriter {
    //Firebase data manipulators
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private final String SERVER_ADDRESS = "https://b07project-39fda-default-rtdb.firebaseio.com/";


    public ProductReaderWriter(){
        mAuth = FirebaseAuth.getInstance();
        this.data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference();
    }

    //Create Latest product object

    //
    public Product fetchProduct(String product_id){
        final Product[] Product = new Product[1];
        data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference().child("Products").child(product_id);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datasnap : dataSnapshot.getChildren()) {
                    Product[0] = datasnap.getValue(Product.class);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return Product[0];
    }

    public boolean writeToFirebase(Product newProduct, String storeID){
        mAuth = FirebaseAuth.getInstance();
        String productID = newProduct.getProductID();
        String userID = mAuth.getCurrentUser().getUid().toString(); //Remember For later null ptr.
        data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference();
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                data.child("Products").child(productID).setValue(newProduct);
                data.child(storeID).child("Products").child(productID).setValue(productID);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Cannot connect to server and set type.");
            }
        });
        return true;
    }


    public boolean writeProduct(Product product, String product_id, String StoreUUID){
        data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference();
        data.child("Products").child(product_id).setValue(product);
        data.child("Store").child(StoreUUID).child("Products").child(product_id).setValue(product_id);

        return true;
    }

    public boolean removeProduct(String product_id,String StoreUUID){
        data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference();
        data.child("Products").child(product_id).removeValue();
        data.child("Store").child(StoreUUID).child("Products").child(product_id).removeValue();
        return true;
    }

    //
}
