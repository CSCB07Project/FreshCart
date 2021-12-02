package com.example.myapplication.Dashboard_Seller.models;

import com.example.myapplication.Seller;
import com.example.myapplication.Store;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SellerAccountReaderWriter {
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private final String SERVER_ADDRESS = "https://b07project-39fda-default-rtdb.firebaseio.com/";

    public SellerAccountReaderWriter(){
        mAuth = FirebaseAuth.getInstance();
    }

    public Seller fetchLatest(String userid){
        final Seller[] getSeller = new Seller[1];
        try{
            data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference().child("user").child(userid);
            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot datasnap : dataSnapshot.getChildren()) {
                        getSeller[0] = datasnap.getValue(Seller.class);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    getSeller[0] = null;
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            return getSeller[0];
        }catch(Exception e){
            getSeller[0] = null;
        }
        return getSeller[0];
    }

}
