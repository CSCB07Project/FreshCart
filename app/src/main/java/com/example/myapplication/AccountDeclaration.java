package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountDeclaration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_declaration);

        mAuth = FirebaseAuth.getInstance();
        userid = mAuth.getCurrentUser().getUid().toString();
    }

    public void Seller(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance("https://b07project-39fda-default-rtdb.firebaseio.com/").getReference();
        ref.child("Users").child(String.valueOf(userid)).child("accountType").setValue(0);
        Intent intent = new Intent(AccountDeclaration.this, SellerDashboard.class);
        startActivity(intent);
    }

    public void Customer(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance("https://b07project-39fda-default-rtdb.firebaseio.com/").getReference();
        ref.child("Users").child(String.valueOf(userid)).child("accountType").setValue(1);
        Intent intent = new Intent(AccountDeclaration.this, BuyerDashboard.class);
        startActivity(intent);
    }
}