package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introscreen);
        mAuth = FirebaseAuth.getInstance();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadUser();
            }
        }, 4000);

        Order one = new Order("o1","testid", "2VKiG2IsDihiMKKAkQgWW1ZO8Br2",0, 7.23f,"Porom's Test Store", "Dec 03");
        Order two = new Order("o2","testid", "2VKiG2IsDihiMKKAkQgWW1ZO8Br2",1, 3.23f,"Seron's Test Store", "Dec 04");
        Order three = new Order("o3","testid", "2VKiG2IsDihiMKKAkQgWW1ZO8Br2",2, 5.33f,"Andrew's Test Store", "Dec 05");
        Order four = new Order("o4","testid", "2VKiG2IsDihiMKKAkQgWW1ZO8Br2",2, 10.2f,"Nandhu's Test Store", "Dec 06");
        Order five = new Order("o5","testid", "2VKiG2IsDihiMKKAkQgWW1ZO8Br2",1, 72.4f,"Billy's Test Store", "Dec 07");
        Order six = new Order("o6","testid", "2VKiG2IsDihiMKKAkQgWW1ZO8Br2",-1, 3.2f,"John's Test Store", "Dec 08");
        Order seven = new Order("o7","testid", "2VKiG2IsDihiMKKAkQgWW1ZO8Br2",1, 5.6f,"Emily's Test Store", "Dec 09");
        Order eight = new Order("o8","testid", "2VKiG2IsDihiMKKAkQgWW1ZO8Br2",2, 8.2f,"Lily's Test Store", "Dec 10");


        one.addProduct("0"+ "_key", 3);
        one.addProduct("1"+ "_key", 3);
        one.addProduct("2"+ "_key", 3);

        two.addProduct("0"+ "_key", 3);
        two.addProduct("1"+ "_key", 6);


        three.addProduct("0" + "_key", 3);
        three.addProduct("2"+ "_key", 7);


        four.addProduct("0"+ "_key", 1);
        four.addProduct("1"+ "_key", 1);
        four.addProduct("3"+ "_key", 1);


        five.addProduct("0"+ "_key", 5);

        six.addProduct("5"+ "_key", 3);

        seven.addProduct("3"+ "_key", 3);

        eight.addProduct("2"+ "_key", 3);
        eight.addProduct("0"+ "_key", 3);


        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        ref.child("Orders").child(one.orderid).setValue(one);
        ref.child("Orders").child(two.orderid).setValue(two);
        ref.child("Orders").child(three.orderid).setValue(three);
        ref.child("Orders").child(four.orderid).setValue(four);
        ref.child("Orders").child(five.orderid).setValue(five);
        ref.child("Orders").child(six.orderid).setValue(six);
        ref.child("Orders").child(seven.orderid).setValue(seven);
        ref.child("Orders").child(eight.orderid).setValue(eight);



    }

    public void loadUser(){
        Intent intent = new Intent(this, LoadingUserActivity.class);
        startActivity(intent);
    }
}