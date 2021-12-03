package com.example.myapplication.Dashboard_Seller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.*;
import com.example.myapplication.Dashboard_Seller.models.ProductReaderWriter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class CreateProduct extends AppCompatActivity {
    private String productID;
    private String productName;
    private String productDescription;
    private String productImageUrl;
    private String productPrice;
    private String store;

    //UI BINDERS
    EditText productNameField, descriptionField, priceField;
    Button NextBtn, UploadImg; //Next btn uploads!

    private Uri imageurl = null;
    //Authentication
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private StorageReference storagedata;
    private final String SERVER_ADDRESS = "https://b07project-39fda-default-rtdb.firebaseio.com/";
    private final String FILESERVER_ADDRESS = "gs://b07project-39fda.appspot.com";
    private String userid;

    //Image Code

    private static final int image_req = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_store);

        //Bind
        NextBtn = (Button) findViewById(R.id.UploadBtnProduct);
        UploadImg = (Button) findViewById(R.id.productimgupload);
        productNameField = (EditText) findViewById(R.id.addproductname);
        descriptionField = (EditText) findViewById(R.id.addproductdescript);
        priceField = (EditText) findViewById(R.id.Addprice);

        //Auth Setup
        mAuth = FirebaseAuth.getInstance();

        storagedata = FirebaseStorage.getInstance(FILESERVER_ADDRESS).getReference(); //no folder
        data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference();


        userid = mAuth.getCurrentUser().getUid().toString();
    }

    public boolean CheckUserInput(String name, String description, String price){
        String[] fields = new String[]{name, description, price};
        EditText[] textFields = new EditText[]{productNameField, descriptionField, priceField};
        for(int i=0;i<fields.length;i++){
            if(fields[i].length() == 0 || fields[i] == null){
                textFields[i].setError("Enter " + fields[i]  + " name");
                return false;
            }
            if(fields[i].length() < 1) {
                textFields[i].setError("Enter minimum length value");
                return false;
            }
        }

        try{
            float val = Float.parseFloat(price);
        }catch(NumberFormatException e){
            priceField.setError("Enter a valid price(Number)");
            return false;
        }

        return true;
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



    public void AddProduct(View view){//Add btn
        productName = productNameField.getText().toString();
        productDescription = descriptionField.getText().toString();
        productPrice = priceField.getText().toString();


        if(imageurl == null){
            Toast.makeText(CreateProduct.this, "Select an image", Toast.LENGTH_LONG).show();
        }

        if(CheckUserInput(productName, productDescription,productPrice) && imageurl != null){
            float product_price =  Float.parseFloat(productPrice);
            String ProductID = UUID.randomUUID().toString().replaceAll("-", "");
            String producturlname =  ProductID +"." +gettype(imageurl);
            String productLocationURL = "gs://b07project-39fda.appspot.com/" + producturlname;

            StorageReference fileref = storagedata.child(producturlname);
            fileref.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //Create entry in database.
                    ProductReaderWriter writeData = new ProductReaderWriter();
                    Product newProduct = new Product(ProductID, productName, productDescription, product_price,"-1" ,productLocationURL);
                    writeData.writeProduct(newProduct, ProductID);
                    Toast.makeText(CreateProduct.this, "Product has been added", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }


    }

    private String gettype(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    public void backbtn(View view){
        finish();
    }
}