package com.example.myapplication.Dashboard_Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Dashboard_Seller.models.ProductReaderWriter;
import com.example.myapplication.Dashboard_Seller.models.StoreReaderWriter;
import com.example.myapplication.Product;
import com.example.myapplication.R;
import com.example.myapplication.Store;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
    private static final int image_req = 1;
    private Uri imageurl = null;

    private String uuid_store;
    //UI elements
    Button NextBtn;
    EditText nameField, addressField, countryField, provinceField, cityField, postalField, CNfield;

    //Authentication
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private String userid;
    private StorageReference storagedata;
    private final String FILESERVER_ADDRESS = "gs://b07project-39fda.appspot.com";



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

        storagedata = FirebaseStorage.getInstance(FILESERVER_ADDRESS).getReference(); //no folder


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

    public void Exit(View view){ //Possible issue in future?
        Intent intent = new Intent(CreateNewStore.this, StoreLoader.class);
        startActivity(intent);
    }

    public void getImage(View view){ //Get btn
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, image_req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == image_req && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageurl = data.getData();
        }
    }

    private String gettype(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
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

        if(imageurl == null){
            Toast.makeText(CreateNewStore.this, "Select an image", Toast.LENGTH_LONG).show();
        }

        if(CheckUserInput(name,address,country,province,city,postalCode,contactNumber) && imageurl != null){
            String id = UUID.randomUUID().toString().replaceAll("-", "") ;
            uuid_store = id;
            String storeurlname =  id +"." +gettype(imageurl);
            StorageReference fileref = storagedata.child(storeurlname);
            fileref.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //Create entry in database.
                    fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            Store newStore = new Store(id,name,"NA",url,address,country,province,city,postalCode);
                            operator.writeToFirebase(newStore);
                            Toast.makeText(CreateNewStore.this, "Store has been created.", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                }
            });
            goToSellerDashboard();
            finish();
        }
    }



    public void goToSellerDashboard(){
        Intent intent = new Intent(CreateNewStore.this, SellerDashboard.class);
        intent.putExtra("uuid", uuid_store);


        startActivity(intent);
        finish();
    }


}