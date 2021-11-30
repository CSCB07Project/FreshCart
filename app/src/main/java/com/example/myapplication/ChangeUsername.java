package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeUsername extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_username);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        //FirebaseDatabase.getInstance().getReference("Users").child(uid).child(String.valueOf("username")).setValue("input");

        EditText eT = (EditText) findViewById(R.id.ETUN);

        Button ok = (Button) findViewById(R.id.OKUN);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = eT.getText().toString();
                if(input == null || input.length() <2 ){
                    Toast toast = Toast.makeText(ChangeUsername.this, "not valid username", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else{
                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child(String.valueOf("username")).setValue(input);

                    Intent intent = new Intent(ChangeUsername.this, BuyerDashboard.class);
                    startActivity(intent);
                }
            }
        });

    }
}
