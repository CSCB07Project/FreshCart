package com.example.myapplication.loginmodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.AccountDeclaration;
import com.example.myapplication.BuyerDashboard;
import com.example.myapplication.Dashboard_Seller.StoreLoader;
import com.example.myapplication.LoadingUserActivity;
import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Contract.View {
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private ImageButton signin;
    private EditText editEmail, editPassword;
    private ImageButton register;
    private ProgressBar progressBar;
    private TextView invalid;

    private int AccountType;
    private LoginModel model;
    private LoginPresenter presenter;

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
        model = new LoginModel();
        presenter = new LoginPresenter(model, this);

        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public String getUsername() {
        return editEmail.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return editPassword.getText().toString().trim();
    }

    @Override
    public void displayError(String message) {
        invalid.setText(message);
        invalid.setVisibility(View.VISIBLE);
    }

    @Override
    public void router(int type){
        if(type == -1){
            Intent intent = new Intent(LoginActivity.this, AccountDeclaration.class);
            startActivity(intent);
        }else if(type == 0){
            Intent intent = new Intent(LoginActivity.this, StoreLoader.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(LoginActivity.this, BuyerDashboard.class);
            startActivity(intent);
        }
    }
    @Override
    public void onClick (View v) {
        switch(v.getId()){
            case R.id.login_registerbtn:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.loginbtn:
                presenter.authenticateLogin(getUsername(), getPassword());
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(LoginActivity.this, LoadingUserActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}