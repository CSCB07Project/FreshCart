package com.example.myapplication.Dashboard_Seller;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.Dashboard_Seller.models.ProductReaderWriter;
import com.example.myapplication.Product;
import com.example.myapplication.R;
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
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.UUID;

public class EditDeleteProduct extends AppCompatActivity {
    private String productID;
    private String productName;
    private String productDescription;
    private String productImageUrl;
    private String productPrice;
    private String store;

    //UI BINDERS
    EditText productNameField, descriptionField, priceField;
    Button NextBtn; //Next btn uploads!
    ImageView UploadImg;

    private Uri imageurl = null;

    //Authentication
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private StorageReference storagedata;
    private final String SERVER_ADDRESS = "https://b07project-39fda-default-rtdb.firebaseio.com/";
    private final String FILESERVER_ADDRESS = "gs://b07project-39fda.appspot.com";

    //UI RESULT
    private static final int image_req = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_product);

        //Bind
        NextBtn = (Button) findViewById(R.id.buttonUpdate);
        UploadImg = (ImageView) findViewById(R.id.imageView18);
        productNameField = (EditText) findViewById(R.id.addnewproductname);
        descriptionField = (EditText) findViewById(R.id.addnewproductdescript);
        priceField = (EditText) findViewById(R.id.Addnewprice);

        //Auth Setup
        mAuth = FirebaseAuth.getInstance();
        storagedata = FirebaseStorage.getInstance(FILESERVER_ADDRESS).getReference(); //no folder
        data = FirebaseDatabase.getInstance(SERVER_ADDRESS).getReference();

        //Load from intent.
        productID = getIntent().getStringExtra("productID");
        productName = getIntent().getStringExtra("productName");
        productDescription = getIntent().getStringExtra("productDescription");
        productImageUrl = getIntent().getStringExtra("productURL");
        productPrice = getIntent().getStringExtra("productPrice");
        store = getIntent().getStringExtra("productStore");

        Glide.with(this)
                .load(productImageUrl)
                .placeholder(R.drawable.cartlogo)
                .dontAnimate()
                .into(UploadImg);

    }

    public boolean CheckUserInput(String name, String description, String price){
        String[] fields = new String[]{name, description, price};
        EditText[] textFields = new EditText[]{productNameField, descriptionField, priceField};
        for(int i=0;i<fields.length;i++){
            if(fields[i].length() < 1 && fields[i].length()!=0) { //
                textFields[i].setError("Enter minimum length value");
                return false;
            }
        }
        if(fields[2].length() !=0){ //Check if not-empty
            try{
                float val = Float.parseFloat(price);
            }catch(NumberFormatException e){
                priceField.setError("Enter a valid price(Number)");
                return false;
            }
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
        String new_productName = productNameField.getText().toString();
        String new_productDescription = descriptionField.getText().toString();
        String new_productPrice = priceField.getText().toString();

        if(new_productName.length() == 0 && new_productDescription.length() == 0 && new_productPrice.length() == 0){
            Toast.makeText(EditDeleteProduct.this, "No changes has been made", Toast.LENGTH_LONG).show();
            finish();
        }

        if(CheckUserInput(new_productName, new_productDescription,new_productPrice)){
            //Check the type.
            if(!(new_productName.length() == 0 || new_productName == null)){
                productName = new_productName;
            }
            if(!(new_productDescription.length() == 0 || new_productDescription == null)){
                productDescription = new_productDescription;
            }
            if(!(new_productPrice.length() == 0 || new_productPrice == null)){
                productPrice = new_productPrice;
            }


            String ProductID = productID;

            if(imageurl == null){ //User did not select an image
                ProductReaderWriter writeData = new ProductReaderWriter();
                Product newProduct = new Product(ProductID, productName, productDescription, Float.parseFloat(productPrice),store, productImageUrl);
                writeData.writeProduct(newProduct, ProductID,store);
                Toast.makeText(EditDeleteProduct.this, "Product has been updated", Toast.LENGTH_LONG).show();

            }else{
                String producturlname =  ProductID +"." +gettype(imageurl);
                StorageReference fileref = storagedata.child(producturlname);
                fileref.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //Create entry in database.
                        fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                ProductReaderWriter writeData = new ProductReaderWriter();
                                Product newProduct = new Product(ProductID, productName, productDescription, Float.parseFloat(productPrice),store,url);
                                writeData.writeProduct(newProduct, ProductID, store);
                                Toast.makeText(EditDeleteProduct.this, "Product has been updated", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });
                    }
                });
            }

        }

        finish();
    }

    public void remove(View view){
        ProductReaderWriter op = new ProductReaderWriter();
        op.removeProduct(productID, store);
        Toast.makeText(EditDeleteProduct.this, "Product has been removed", Toast.LENGTH_LONG).show();
        finish();
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