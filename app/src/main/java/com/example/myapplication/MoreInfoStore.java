package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MoreInfoStore extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.moreinfostore);

        String storeId = "";
        String eInfo = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            storeId = extras.getString("storeID");
            eInfo = extras.getString("eInfo");
        }

        /*
        FirebaseDatabase.getInstance().getReference("Seller").child(storeId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String storeName = "", storeBannerUrl = "", storeAddress = "";
                String storeContact = "", storeDescription = "", storeCountry = "", storeProvince = "", storeCity = "", storePostal = "";
                String storeID = "";
                    if (snapshot.hasChild("storeName"))
                        storeName = snapshot.child("storeName").getValue().toString();
                    if (snapshot.hasChild("storeAddress"))
                        storeAddress = snapshot.child("storeAddress").getValue().toString();
                    if (snapshot.hasChild("storeBannerUrl"))
                        storeBannerUrl = snapshot.child("storeBannerUrl").getValue().toString();
                    if (snapshot.hasChild("storeID"))
                        storeID = snapshot.child("storeID").getValue().toString();
                    if (snapshot.hasChild("storeContact"))
                        storeContact = snapshot.child("storeContact").getValue().toString();

                    if (snapshot.hasChild("storeDescription"))
                        storeDescription = snapshot.child("storeDescription").getValue().toString();
                    if (snapshot.hasChild("storeCountry"))
                        storeCountry = snapshot.child("storeCountry").getValue().toString();
                    if (snapshot.hasChild("storeProvince"))
                        storeProvince = snapshot.child("storeProvince").getValue().toString();
                    if (snapshot.hasChild("storeCity"))
                        storeCity = snapshot.child("storeCity").getValue().toString();
                    if (snapshot.hasChild("storePostal"))
                        storePostal = snapshot.child("storePostal").getValue().toString();
                xInfo[0] =
                        "Store ID:                   " + storeID +
                                "\nStore Name:            " + storeName +
                                "\nStore Description:   " + storeDescription +
                                "\nStore Contact:         " + storeContact +
                                "\nStore Address:        " + storeAddress +
                                "\nStore Postal:            " + storePostal +
                                "\nStore City:                " + storeCity +
                                "\nStore Province:       " + storeProvince +
                                "\nStore Country:         " + storeCountry;
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
         */

        TextView tv = (TextView) findViewById(R.id.storeMI);
        tv.setText(eInfo);
    }
}


