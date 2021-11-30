package com.example.myapplication;

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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class PInfo extends AppCompatActivity {

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


    }
}
