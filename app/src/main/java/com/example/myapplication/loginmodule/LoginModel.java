package com.example.myapplication.loginmodule;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginModel implements Contract.Model{
    @Override
    public void loginUser(String email, String password, LoginActivity view, LoginPresenter presenter) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    presenter.determineSuccess("Success");
                }
                else{
                    presenter.determineSuccess("Credentials Not Valid");
                }
            }
        });
    }

    @Override
    public void getAccountType(LoginPresenter presenter) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid().toString();
        //Get the usertype from the real time db
        DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                String obtained = snapshot.child("accountType").getValue().toString();
                int type = Integer.parseInt(obtained);
                presenter.startRouting(type);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DATABASE ERROR", error.toString());
            }
        });
    }
}
