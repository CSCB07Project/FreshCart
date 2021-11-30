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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_name);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        //FirebaseDatabase.getInstance().getReference("Users").child(uid).child(String.valueOf("username")).setValue("input");

        EditText eT1 = (EditText) findViewById(R.id.ETN1);
        EditText eT2 = (EditText) findViewById(R.id.ETN2);

        Button ok = (Button) findViewById(R.id.OKN);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = eT1.getText().toString();
                String input2 = eT2.getText().toString();
                if(input1 == null || input1.length() < 2 || input2 == null || input2.length() < 2 ){
                    Toast toast = Toast.makeText(ChangeName.this, "not valid name", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else{
                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child(String.valueOf("firstName")).setValue(input1);
                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child(String.valueOf("lastName")).setValue(input2);

                    Intent intent = new Intent(ChangeName.this, BuyerDashboard.class);
                    startActivity(intent);
                }
            }
        });

    }
}
