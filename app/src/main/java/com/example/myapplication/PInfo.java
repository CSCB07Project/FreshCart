package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;

public class PInfo extends AppCompatActivity {

    String storeName1 = "", storeBannerUrl = "", storeAddress = "";
    String storeContact = "", storeDescription = "", storeCountry = "", storeProvince = "", storeCity = "", storePostal = "";
    String storeID = "";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("Products");

    ArrayList<String> products = new ArrayList<>();
    ArrayList<String> mImageUrls = new ArrayList<>();
    ArrayList<String> mPrice = new ArrayList<>();
    ArrayList<String> mImages = new ArrayList<>();
    ArrayList<String> mDesc = new ArrayList<>();
    ArrayList<String> mId = new ArrayList<>();
    RecyclerViewAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinfomain);

        String storeName = "";
        String storeId = "";
        String storeBanner = "";
        String eInfo = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            storeName = extras.getString("storeName");
            storeId = extras.getString("storeID");
            storeBanner = extras.getString("storeBanner");
            eInfo = extras.getString("eInfo");
        }
        ImageView iv = (ImageView) findViewById(R.id.bannermore);
        Glide.with(this)
                .load(storeBanner)
                .placeholder(R.drawable.cartlogo)
                .dontAnimate()
                .into(iv);

        TextView tv = (TextView) findViewById(R.id.storeNN);
        tv.setText(storeName);


        Button btn = (Button) findViewById(R.id.nextMore);
        String finalEInfo = eInfo;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PInfo.this, MoreInfoStore.class);
                intent.putExtra("eInfo", finalEInfo);
                startActivity(intent);
            }
        });

        FirebaseDatabase.getInstance().getReference("Seller").child(storeId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("storeName"))
                    storeName1 = snapshot.child("storeName").getValue().toString();
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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        initRecyclerView();

    }



    private void initRecyclerView(){

        RecyclerView recyclerView = findViewById(R.id.recyclerViewBuyer1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), Product.class)
                        .build();
        adapter = new RecyclerViewAdapter2(options);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
