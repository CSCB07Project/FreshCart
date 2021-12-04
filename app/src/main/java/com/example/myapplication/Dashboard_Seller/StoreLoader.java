package com.example.myapplication.Dashboard_Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.AccountDeclaration;
import com.example.myapplication.Dashboard_Seller.models.SellerAccountReaderWriter;
import com.example.myapplication.Dashboard_Seller.models.StoreReaderWriter;
import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity2;
import com.example.myapplication.Seller;
import com.example.myapplication.Store;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class StoreLoader extends AppCompatActivity {
    private Store userStore;
    private Seller seller_account;
    private FirebaseAuth mAuth;
    private DatabaseReference data;

    FloatingActionButton addStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_loader);
        mAuth = FirebaseAuth.getInstance();
        load_status();
    }




    public void load_status(){
        data = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid().toString());
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                String obtained;
                try{
                    obtained = snapshot.child("storeID").getValue().toString();
                    if(!(obtained == null || obtained.compareTo("-1") == 0)){
                        router(true, obtained);
                    }

                }catch(Exception e){
                    FirebaseAuth.getInstance().signOut();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Cannot connect to server and set type.");
            }
        });

    }



    public void router(boolean status, String Store_UUID){
        if(status){ //Go to the store.
            Intent intent = new Intent(StoreLoader.this, SellerDashboard.class);
            intent.putExtra("uuid", Store_UUID);
            startActivity(intent);
            finish();
        }
    }

    public void CreateStorePage(View view){
        Intent intent = new Intent(StoreLoader.this, CreateNewStore.class);
        startActivity(intent);
        finish();
    }
}