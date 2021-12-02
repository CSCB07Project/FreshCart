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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PInfo extends AppCompatActivity {

    String storeName1 = "", storeBannerUrl = "", storeAddress = "";
    String storeContact = "", storeDescription = "", storeCountry = "", storeProvince = "", storeCity = "", storePostal = "";
    String storeID = "";

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

        initImageBitmaps();


    }

    private void initImageBitmaps(){
        mImageUrls.add("https://cdn.pixabay.com/photo/2016/10/07/14/11/tangerines-1721633_1280.jpg");
        mImages.add("Orange");
        mDesc.add("A bag of 5 oranges");
        mPrice.add("5");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2013/07/05/12/12/apples-143457_1280.jpg");
        mImages.add("Apple");
        mDesc.add("A bag of 6 apples");
        mPrice.add("6");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2015/08/18/23/09/bananas-895072_1280.jpg");
        mImages.add("Banana");
        mDesc.add("A bag of 12 bananas");
        mPrice.add("7");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830_1280.jpg");
        mImages.add("Lemon");
        mDesc.add("A bag of 10 lemons");
        mPrice.add("8");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2012/02/28/15/43/rice-18431_1280.jpg");
        mImages.add("Basmati Rice");
        mDesc.add("A bag of Basmati Rice");
        mPrice.add("20");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2016/07/16/16/30/spinach-1522283_1280.jpg");
        mImages.add("Spinach");
        mDesc.add("A bag of Spinach");
        mPrice.add("6");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2018/05/21/21/23/garlic-3419544_1280.jpg");
        mImages.add("Garlic");
        mDesc.add("A packet of Garlic");
        mPrice.add("7");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2016/07/11/00/18/carrots-1508847_1280.jpg");
        mImages.add("Carrot");
        mDesc.add("A bag of 12 Carrots");
        mPrice.add("8");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2014/08/06/20/32/potatoes-411975_1280.jpg");
        mImages.add("Potato");
        mDesc.add("A bag of 10 Potatoes");
        mPrice.add("5");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2018/12/07/00/19/savoy-3860933_1280.jpg");
        mImages.add("Cabbage");
        mDesc.add("A bag of Cabbage");
        mPrice.add("6");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2016/05/12/16/34/ginger-1388002_1280.jpg");
        mImages.add("Ginger");
        mDesc.add("A packet of Ginger");
        mPrice.add("7");
        mId.add("0");

        mImageUrls.add("https://cdn.pixabay.com/photo/2016/05/11/20/35/bell-peppers-1386467_1280.jpg");
        mImages.add("Red Bell Peppers");
        mDesc.add("A bag of 6 Red Bell Peppers");
        mPrice.add("8");
        mId.add("0");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewBuyer1);
        RecyclerViewAdapter2 adapter = new RecyclerViewAdapter2(mImages, mImageUrls, mPrice, mDesc,mId, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}

