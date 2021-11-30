package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LoadingUserActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private int AccountType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_loading_user);//Deprecated
        mAuth = FirebaseAuth.getInstance();
    }

    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener(){
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            Log.d("Test", "ENTERED LOADINGSTATE");
            FirebaseUser firebaseUser = mAuth.getCurrentUser();
            if (firebaseUser == null) {
                Intent intent = new Intent(LoadingUserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }else{
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                //Log.d("Email", firebaseUser.getEmail());
                getAccountDeclaration();
                finish();
            }
        }
    };

    //Refactor and put into Account function.
    public void getAccountDeclaration(){
        String uid = mAuth.getCurrentUser().getUid().toString();
        //Get the usertype from the real time db
        data = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                String obtained = snapshot.child("accountType").getValue().toString();
                AccountType = Integer.parseInt(obtained);
                router(AccountType);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Cannot connect to server and set type.");
            }
        });
    }

    public void router(int type){
        Log.d("Type", ""+type);
        if(type == -1){
            Intent intent = new Intent(LoadingUserActivity.this, AccountDeclaration.class);
            startActivity(intent);
        }else if(type == 0){
            Intent intent = new Intent(LoadingUserActivity.this, SellerDashboard.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(LoadingUserActivity.this, BuyerDashboard.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}