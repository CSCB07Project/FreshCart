package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introscreen);

        Store s1 = new Store(1, "store 1", "this is the first store", "www.store1.com");
        Store s2 = new Store(2, "store 2", "this is the second store", "www.store2.com");
        Store s3 = new Store(3, "store 3", "this is the third store", "www.store3.com");
        Store s4 = new Store(4, "store 4", "this is the fourth store", "www.store4.com");
        Store s5 = new Store(5, "store 5", "this is the fifth store", "www.store5.com");
        Store s6 = new Store(6, "store 6", "this is the sixth store", "www.store6.com");
        Store s7 = new Store(7, "store 7", "this is the seventh store", "www.store7.com");
        Store s8 = new Store(8, "store 8", "this is the eighth store", "www.store8.com");
        DatabaseReference ref = FirebaseDatabase.getInstance("https://b07project-39fda-default-rtdb.firebaseio.com/").getReference();
        ref.child("Seller").child(String.valueOf(s1.storeID)).setValue(s1);
        ref.child("Seller").child(String.valueOf(s2.storeID)).setValue(s2);
        ref.child("Seller").child(String.valueOf(s3.storeID)).setValue(s3);
        ref.child("Seller").child(String.valueOf(s4.storeID)).setValue(s4);
        ref.child("Seller").child(String.valueOf(s5.storeID)).setValue(s5);
        ref.child("Seller").child(String.valueOf(s6.storeID)).setValue(s6);
        ref.child("Seller").child(String.valueOf(s7.storeID)).setValue(s7);
        ref.child("Seller").child(String.valueOf(s8.storeID)).setValue(s8);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadUser();
            }
        }, 4000);
    }

    public void loadUser(){
        Intent intent = new Intent(this, LoadingUserActivity.class);
        startActivity(intent);
    }
}