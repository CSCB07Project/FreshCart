package com.example.myapplication.Dashboard_Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Dashboard_Seller.models.ProductReaderWriter;
import com.example.myapplication.Dashboard_Seller.models.StoreReaderWriter;
import com.example.myapplication.R;
import com.example.myapplication.Store;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import java.util.ArrayList;

public class CreateNewStore extends AppCompatActivity {
    //User Provided Data
    //User Provided Data
    private String name;
    private String address;
    private String country;
    private String province;
    private String city;
    private String postalCode;
    private String contactNumber;

    //UI elements
    Button NextBtn;
    EditText nameField, addressField, countryField, provinceField, cityField, postalField, CNfield;

    //Authentication
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private String userid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_new_store);

        //Bind
        NextBtn = (Button) findViewById(R.id.RegisterButton);
        nameField = (EditText) findViewById(R.id.StoreName);
        addressField = (EditText) findViewById(R.id.StoreAddress);
        countryField = (EditText) findViewById(R.id.StoreCountry);
        provinceField = (EditText) findViewById(R.id.StoreProvince);
        cityField = (EditText) findViewById(R.id.StoreCity);
        postalField = (EditText) findViewById(R.id.StorePostalCode);
        CNfield = (EditText) findViewById(R.id.ContactNumber);

        mAuth = FirebaseAuth.getInstance();
        userid = mAuth.getCurrentUser().getUid().toString();

    }

    public boolean CheckUserInput(String name, String address, String country, String province, String city, String postalCode, String contactNumber){
        String[] fields = new String[]{name, address, country, province, city, postalCode, contactNumber};
        EditText[] textFields = new EditText[]{nameField, addressField, countryField, provinceField, cityField, postalField, CNfield};

        for(int i=0;i<fields.length;i++){
            if(fields[i].length() == 0 || fields[i] == null){
                textFields[i].setError("Enter " + fields[i]  + " name");
                return false;
            }

            if(fields[i].length() < 2) {
                textFields[i].setError("Enter minimum length string");
                return false;
            }

        }
        return true;
    }

    public void Exit(View view){
        Intent intent = new Intent(CreateNewStore.this, StoreLoader.class);
        startActivity(intent);
    }

    public void AddToDb(View view){
        name = nameField.getText().toString();
        address = addressField.getText().toString();
        country = countryField.getText().toString();
        province = provinceField.getText().toString();
        city = cityField.getText().toString();
        postalCode = postalField.getText().toString();
        contactNumber = CNfield.getText().toString();

        StoreReaderWriter operator = new StoreReaderWriter();

        if(CheckUserInput(name,address,country,province,city,postalCode,contactNumber)){
            String id = UUID.randomUUID().toString().replaceAll("-", "") ;
            String tempBanner = "https://cdn.pixabay.com/photo/2016/03/02/20/13/grocery-1232944_960_720.jpg";
            Store newStore = new Store(id,name,"NA",tempBanner,address,country,province,city,postalCode);
            //Write
            operator.writeToFirebase(newStore);
            goToSellerDashboard();
            finish();
        }

    }

    public void goToSellerDashboard(){
        Intent intent = new Intent(CreateNewStore.this, SellerDashboard.class);
        startActivity(intent);
    }


}