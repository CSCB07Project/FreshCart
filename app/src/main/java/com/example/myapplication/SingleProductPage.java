package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SingleProductPage extends AppCompatActivity {
    static int count = 0;
    Button add;
    Button minus;
    //TextView incrementdecrement = (TextView) findViewById(R.id.itemcounter);
    TextView incrementdecrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_page);
        String productimage = "";
        String productname= "";
        String productprice= "";
        //String productdescription = "";
        //String productid = "";
       // String productstore = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productimage = extras.getString("mImages");
            productname = extras.getString("mImageNames");
            productprice = extras.getString("mPrice");
           // productdescription = extras.getString("productDescription");
            //productid = extras.getString("productID");
        }

            TextView tv = (TextView) findViewById(R.id.ProductNameText);
            tv.setText(productname);
            TextView tv1 = (TextView) findViewById(R.id.productnameinfo);
            tv1.setText(productname);
            TextView tv2 = (TextView) findViewById(R.id.productdescriptioninfo);
            //tv2.setText(productdescription);
            TextView tv3 = (TextView) findViewById(R.id.productpriceinfo);
            tv3.setText(productprice);
            incrementdecrement = (TextView)findViewById(R.id.itemcounter);
            incrementdecrement.setText(String.valueOf(count));
            ImageView iv = (ImageView) findViewById(R.id.productpictureiv);
            Glide.with(this)
                    .load(productimage)
                    .placeholder(R.drawable.cartlogo)
                    .dontAnimate()
                    .into(iv);

//            String finalProductid = productid;
//            String finalProductname = productname;
//            String finalProductdescription = productdescription;
//            String finalProductprice = productprice;
//            String finalProductimage = productimage;
//            String finalProductstore = productstore;
//
//            FirebaseDatabase.getInstance().getReference("Product").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    for (DataSnapshot item : snapshot.getChildren()) {
//                        String productimage = "";
//                        String productdescription = "";
//                        String productname = "";
//                        String productprice = "";
//                        String productid = "";
//                        String productstore = "";
//                        if (item.hasChild("productDescription"))
//                            productdescription = item.child("productDescription").getValue().toString();
//                            tv2.setText(productdescription);
//                        if (item.hasChild("productName"))
//                            productname = item.child("productName").getValue().toString();
//                            tv.setText(productname);
//                            tv1.setText(productname);
//                        if (item.hasChild("productPrice"))
//                            productprice = item.child("productPrice").getValue().toString();
//                            tv3.setText(productprice);
//                        if (item.hasChild("productImage"))
//                            productimage = item.child("productImage").getValue().toString();
//                        if(item.hasChild("productID"))
//                            productid = item.child("productID").getValue().toString();
//                        if(item.hasChild("store"))
//                            productstore = item.child("store").getValue().toString();
//                    }
//
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });



        //  FirebaseDatabase.getInstance().getReference("Product").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
        //for (DataSnapshot item : snapshot.getChildren()) {
//                    if (item.hasChild("productDescription"))
//                        tv2.setText(item.child("productDescription").getValue().toString());
//                    if (item.hasChild("productName"))
//                        tv1.setText(item.child("productName").getValue().toString());
//                    if(item.hasChild("productPrice"))
//                        tv3.setText(item.child("productPrice").getValue().toString());

        //  }

        add = (Button) findViewById((R.id.additembutton));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                incrementdecrement.setText(String.valueOf(count));
            }
//                int count = 0;
//                count++;
//                TextView counter = (TextView) findViewById(R.id.itemcounter);
//                counter.setText(String.valueOf(count));


        });
        minus = (Button) findViewById((R.id.minusitembutton));
        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(count != 0) {
                    count = count - 1;
                    incrementdecrement.setText(String.valueOf(count));
                }
            }
        });
    }
}
