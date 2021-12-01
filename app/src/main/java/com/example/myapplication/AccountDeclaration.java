package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountDeclaration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference data;

    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_declaration);

        mAuth = FirebaseAuth.getInstance();
        userid = mAuth.getCurrentUser().getUid().toString();
    }

    //Update user profile type when they enter an account.
    public void Seller(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance("https://b07project-39fda-default-rtdb.firebaseio.com/").getReference();
        UpdateAccountType(0);
        ref.child("Users").child(String.valueOf(userid)).child("accountType").setValue(0);
        Intent intent = new Intent(AccountDeclaration.this, LoginActivity.class);
        startActivity(intent);
    }

    public void Customer(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance("https://b07project-39fda-default-rtdb.firebaseio.com/").getReference();
        UpdateAccountType(1);
        ref.child("Users").child(String.valueOf(userid)).child("accountType").setValue(1);
        Intent intent = new Intent(AccountDeclaration.this, LoginActivity.class);
        startActivity(intent);
    }

    public void UpdateAccountType(int accountType){
        //Read the data from the server, create the required data, and overwrite back.
        data = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                final String emailAddress = snapshot.child("emailAddress").getValue().toString();
                final String firstName = snapshot.child("firstName").getValue().toString();
                final String lastName = snapshot.child("lastName").getValue().toString();
                final String userid = snapshot.child("userid").getValue().toString();
                final String username = snapshot.child("username").getValue().toString();

                //Write depending on account type:
                //0-> seller : 1->buyer
                //String userid, String username, String firstName, String lastName, String emailAddress, int accountType

                //Prepare for write
                DatabaseReference ref = FirebaseDatabase.getInstance("https://b07project-39fda-default-rtdb.firebaseio.com/").getReference();
                if(accountType == 0){
                    Seller new_seller = new Seller(userid,username,firstName,lastName,emailAddress,"-1");
                    ref.child("Users").child(String.valueOf(userid)).setValue(new_seller);
                }else{
                    Buyer new_buyer = new Buyer(userid,username,firstName,lastName,emailAddress);
                    ref.child("Users").child(String.valueOf(userid)).setValue(new_buyer);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Cannot connect to server and set type.");
            }
        });

    }
}