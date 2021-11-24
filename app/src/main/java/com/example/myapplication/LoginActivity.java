package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private ImageButton signin;
    private EditText editEmail, editPassword;
    private ImageButton register;
    private ProgressBar progressBar;
    private TextView invalid;

    private int AccountType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        invalid = (TextView)findViewById(R.id.invalid_login);

        signin = (ImageButton) findViewById(R.id.loginbtn);
        signin.setOnClickListener(this);

        //Add register btn onclick listener after merge with register module
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);

        register = (ImageButton) findViewById(R.id.login_registerbtn);
        register.setOnClickListener(this);
        //progressBar = () // Add in progressbar

        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onClick (View v) {
        switch(v.getId()){
            case R.id.login_registerbtn:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.loginbtn:
                userLogin();
                break;
        }
    }
    public void userLogin(){
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("Login","Successfully Logged in");
                    getAccountDeclaration();
                }
                else{
                    invalid.setVisibility(View.VISIBLE);
                    Log.e("Login", "Credentials Not Valid");
                }
            }
        });
    }

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
            Intent intent = new Intent(LoginActivity.this, AccountDeclaration.class);
            startActivity(intent);
        }else if(type == 0){
            Intent intent = new Intent(LoginActivity.this, SellerDashboard.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(LoginActivity.this, TestDashBoard.class);
            startActivity(intent);
        }
    }

}