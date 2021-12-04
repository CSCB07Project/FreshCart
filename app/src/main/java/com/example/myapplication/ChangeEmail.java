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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_email);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        //FirebaseDatabase.getInstance().getReference("Users").child(uid).child(String.valueOf("username")).setValue("input");

        EditText eT = (EditText) findViewById(R.id.ETMail);

        Button ok = (Button) findViewById(R.id.OKMail);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = eT.getText().toString();

                if(input.length() ==0 || input == null){
                    Toast toast = Toast.makeText(ChangeEmail.this, "Email can't be empty", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                boolean valid_email = android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches();
                if(!valid_email){
                    Toast toast = Toast.makeText(ChangeEmail.this, "not valid Email", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else{
                    user.updateEmail(input);
                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child(String.valueOf("emailAddress")).setValue(input);
                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child("accountType").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.d("TAG", String.valueOf(snapshot.getValue()));
                            if (snapshot.getValue().equals("1")) {
                                Intent intent = new Intent(ChangeEmail.this, BuyerDashboard.class);
                                startActivity(intent);
                            }
                            if (snapshot.getValue().equals("0")) {
                                Intent intent = new Intent(ChangeEmail.this, SellerDashboard.class);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                }

            }
        });

    }
}
