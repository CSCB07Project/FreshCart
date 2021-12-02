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

public class StoreReaderWriter {

    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private final String SERVER_ADDRESS = "https://b07project-39fda-default-rtdb.firebaseio.com/";

    public StoreReaderWriter(){
        mAuth = FirebaseAuth.getInstance();
    }

    public Store fetchLatest(String uuid){
        final Store[] getStore = new Store[1];
        try{
            data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference().child("Store").child(uuid);
            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot datasnap : dataSnapshot.getChildren()) {
                        getStore[0] = datasnap.getValue(Store.class);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    getStore[0] = null;
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            return getStore[0];
        }catch(Exception e){
            getStore[0] = null;
        }
        return getStore[0];
    }


    public boolean writeToFirebase(Store newStore){
        mAuth = FirebaseAuth.getInstance();
        String storeUID = newStore.getStoreID();
        String userID = mAuth.getCurrentUser().getUid().toString(); //Remember For later null ptr.
        data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference();
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                data.child("Store").child(storeUID).setValue(newStore);
                data.child("Users").child(String.valueOf(userID)).child("storeID").setValue(storeUID);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Cannot connect to server and set type.");
            }
        });
        return true;
    }



    public boolean updateSpecific(String store_uid, String datatype, String newContent){
        data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference();
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                data.child("Store").child(store_uid).child(datatype).setValue(newContent);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Cannot connect to server and set type.");
            }
        });
        return true;
    }
}
