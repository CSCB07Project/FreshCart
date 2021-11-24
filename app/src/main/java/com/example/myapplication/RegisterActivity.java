package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.regex.Pattern;
import android.widget.ProgressBar;

public class RegisterActivity extends AppCompatActivity {
    //User Provided Data
    private String email;
    private String password;
    private String confirmedPassword;

    //UI elements
    Button registerBtn;
    EditText emailField, passwordField, confirmedPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get UI components
        registerBtn = (Button) findViewById(R.id.RegisterButton);
        emailField = (EditText) findViewById(R.id.EmailField);
        passwordField = (EditText) findViewById(R.id.PasswordField);
        confirmedPasswordField = (EditText) findViewById(R.id.ConfirmPasswordField);
    }

    public void registerUser(View view){
        //Obtain text from UI components
        email = emailField.getText().toString();
        password = passwordField.getText().toString();
        confirmedPassword = confirmedPasswordField.getText().toString();

        if(!checkUserInput(email, password, confirmedPassword)){
            Log.d("Invalid input detected.", "Invalid ALL");

        }else{
            //Authentication is done in another intent.
            Intent intent = new Intent(this, RegisterActivity2.class);
            intent.putExtra("Email", email);
            intent.putExtra("Password", password);
            startActivity(intent);
        }
    }



    public boolean checkUserInput(String fieldEmail, String fieldPassword, String fieldConfirmPassword){
        //Do some verification checks afterwards.
        boolean valid_email = android.util.Patterns.EMAIL_ADDRESS.matcher(fieldEmail).matches();
        if(fieldEmail.length() ==0 || fieldEmail == null){
            emailField.setError("Enter a email");
            return false;
        }else if(fieldPassword.length() ==0 || fieldPassword == null){
            passwordField.setError("Enter a password");
            return false;
        } else if(fieldConfirmPassword.length()==0 || fieldConfirmPassword == null){
            confirmedPasswordField.setError("Enter confirmed password.");
            return false;
        }else if(!valid_email){
            emailField.setError("Enter a valid email.");
            return false;
        }else if(fieldPassword.length() < 6){
            passwordField.setError("Enter a valid password.");
            return false;
        }else if(!fieldPassword.equals(fieldConfirmPassword)){
            confirmedPasswordField.setError("Password does not match.");
            return false;
        }else{
            return true;
        }
    }
}