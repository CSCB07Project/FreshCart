package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Dashboard_Seller.SellerDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        final int[] type = new int[1];

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        //FirebaseDatabase.getInstance().getReference("Users").child(uid).child(String.valueOf("username")).setValue("input");

        EditText eT0 = (EditText) findViewById(R.id.ETP0);
        EditText eT1 = (EditText) findViewById(R.id.ETP1);
        EditText eT2 = (EditText) findViewById(R.id.ETP2);

        Button ok = (Button) findViewById(R.id.OKP);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input0 = eT0.getText().toString();
                String input1 = eT1.getText().toString();
                String input2 = eT2.getText().toString();

                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("accountType").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("TAG", String.valueOf(snapshot.getValue()));
                        type[0] = Integer.parseInt(String.valueOf(snapshot.getValue()));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(user.getEmail(), input0)
                        .addOnCompleteListener(ChangePassword.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if(input1.length() <6){
                                        Toast toast = Toast.makeText(ChangePassword.this, "Password too short", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                    }
                                    if(!input1.equals(input2)){
                                        Toast toast = Toast.makeText(ChangePassword.this, "Password doesn't match", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                    }
                                    else{
                                        user.updatePassword(input1);

                                        if(type[0] == 1){
                                            Intent intent = new Intent(ChangePassword.this, BuyerDashboard.class);
                                            startActivity(intent);
                                        }

                                        if(type[0] == 0){
                                            Intent intent = new Intent(ChangePassword.this, SellerDashboard.class);
                                            startActivity(intent);
                                        }


                                    }
                                } else {
                                    Toast toast = Toast.makeText(ChangePassword.this, "Wrong Password", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();
                                }
                            }
                        });


            }
        });

    }
}
