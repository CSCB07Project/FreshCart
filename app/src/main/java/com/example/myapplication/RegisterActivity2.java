package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import android.util.Log;
import android.widget.Toast;

public class RegisterActivity2 extends AppCompatActivity {
    //Firebase authentication
    private FirebaseAuth mAuth;

    //User Provided Data
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String username;

    //UI Elements of RegisterActivity2
    Button registerBtn;
    EditText firstNameField, lastNameField, userNameField;
    ProgressBar status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        //Get the UI Components
        registerBtn = (Button) findViewById(R.id.RegisterButton);
        firstNameField = (EditText) findViewById(R.id.FirstName);
        lastNameField = (EditText) findViewById(R.id.LastName);
        userNameField = (EditText) findViewById(R.id.Username);
        status = (ProgressBar) findViewById(R.id.RegStats);
        status.setVisibility(View.INVISIBLE);

        //Get information from intent.
        Intent intent = getIntent();
        email = intent.getStringExtra("Email");
        password = intent.getStringExtra("Password");

        //Load a FireBase Instance
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(View view){
        //Obtain text from UI component fields
        firstName = firstNameField.getText().toString();
        lastName = lastNameField.getText().toString();
        username = userNameField.getText().toString();

        if(checkUserInput(firstName, lastName, username))
        createAccount();
    }

    public void createAccount(){
        status.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                    AccountManager new_account = new AccountManager(userid, username,password, firstName, lastName, email, -1);
                    redirect(0);
                }
            }
        });
    }

    public boolean checkUserInput(String field_firstname, String field_lastname, String field_username){
        //Do some verification checks afterwards.
        if(field_firstname.length() ==0 || field_firstname == null){
            firstNameField.setError("Enter your first name");
            return false;
        }else if(field_lastname.length() ==0 || field_lastname == null) {
            lastNameField.setError("Enter your lastname name");
            return false;
        }else if(field_username.length() ==0 || field_username == null) {
            userNameField.setError("Enter your username");
            return false;
        }else if(field_firstname.length() < 2) {
            firstNameField.setError("Enter your valid name");
            return false;
        }else if(field_lastname.length() < 2) {
            lastNameField.setError("Enter your valid lastname");
            return false;
        }else if(field_username.length() <2) {
            userNameField.setError("Enter a valid username");
            return false;
        }else{
            return true;
        }
    }

    //Redirect to dashboard if success or LoginPage. [Implement]
    public void redirect(int condition){
        Toast.makeText(RegisterActivity2.this, "User Has been successfully been added", Toast.LENGTH_SHORT).show();
        status.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(RegisterActivity2.this, LoginActivity.class);
        startActivity(intent);
    }

}