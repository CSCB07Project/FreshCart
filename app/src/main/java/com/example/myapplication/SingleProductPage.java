package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class


SingleProductPage extends AppCompatActivity {
    int count = 0;
    FloatingActionButton add;
    FloatingActionButton minus;
    TextView incrementdecrement;
    Button addtocart;
    Button yes;
    Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_page);
        String productimage = "";
        String productname = "";
        String productprice = "";
        String productdescription = "";
        String productid = "";
        String storeid = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productimage = extras.getString("mImages");
            productname = extras.getString("mImageNames");
            productprice = "$" + extras.getString("mPrice");
            productdescription = extras.getString("mDesc");
            productid = extras.getString("mId");
            storeid = extras.getString("StoreID");
        }

        TextView tv = (TextView) findViewById(R.id.ProductNameText);
        tv.setText(productname);
        TextView tv1 = (TextView) findViewById(R.id.productnameinfo);
        tv1.setText(productname);
        TextView tv2 = (TextView) findViewById(R.id.productdescriptioninfo);
        tv2.setText(productdescription);
        TextView tv3 = (TextView) findViewById(R.id.productpriceinfo);
        tv3.setText(productprice);
        incrementdecrement = (TextView) findViewById(R.id.itemcounter);
        incrementdecrement.setText(String.valueOf(count));
        ImageView iv = (ImageView) findViewById(R.id.productpictureiv);
        Glide.with(this)
                .load(productimage)
                .placeholder(R.drawable.cartlogo)
                .dontAnimate()
                .into(iv);


        add = (FloatingActionButton) findViewById((R.id.additembutton));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                incrementdecrement.setText(String.valueOf(count));
            }


        });
        minus = (FloatingActionButton) findViewById((R.id.minusitembutton));
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 0) {
                    count = count - 1;
                    incrementdecrement.setText(String.valueOf(count));
                }
            }
        });
        addtocart = (Button) findViewById(R.id.addtocartbutton);
        String finalproductid = productid;
        String finalstoreid = storeid;
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance("https://b07project-39fda-default-rtdb.firebaseio.com/").getReference();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String cartproducts = snapshot.child("Users").child(uid).child("cart").getValue().toString();
                        String newproducttobeaddedstoreid = snapshot.child("Products").child(finalproductid).child("store").getValue().toString();
                        if ((cartproducts == null) || (cartproducts.compareTo("-1") == 0)) {
                            if (count != 0) {
                                ref.child("Users").child(uid).child("cart").child(String.valueOf(finalproductid)).setValue(count);
                                Intent intent = new Intent(SingleProductPage.this, PInfo.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SingleProductPage.this, "Please add an item into your cart", Toast.LENGTH_SHORT).show();
                            }
                        } else if (!(cartproducts == null) || (cartproducts.compareTo("-1") == 0)) {
                            String productincartproductid = snapshot.child("Users").child(uid).child("cart").child(finalproductid).toString();
                            String productincartstoreid = snapshot.child("Products").child(productincartproductid).child("store").getValue().toString();
                            if (newproducttobeaddedstoreid.equals(productincartstoreid)) {
                                if (count != 0) {
                                    ref.child("Users").child(uid).child("cart").child(String.valueOf(finalproductid)).setValue(count);
                                    Intent intent = new Intent(SingleProductPage.this, PInfo.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SingleProductPage.this, "Please add an item into your cart", Toast.LENGTH_SHORT).show();
                                }
                            } else if (!(newproducttobeaddedstoreid.equals(productincartstoreid))) {
                                LayoutInflater inflater = (LayoutInflater)
                                        getSystemService(LAYOUT_INFLATER_SERVICE);
                                View popupView = inflater.inflate(R.layout.addtocart_popupbox, null);
                                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                                boolean focusable = true;
                                PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                                yes = (Button) popupView.findViewById(R.id.YesButton);
                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ref.child("Users").child(uid).child("cart").setValue(null);
                                        ref.child("Users").child(uid).child("cart").child(String.valueOf(finalproductid)).setValue(count);
                                        popupWindow.dismiss();
                                    }
                                });
                                no = (Button) popupView.findViewById(R.id.NoButton);
                                no.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        popupWindow.dismiss();
                                    }
                                });
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("Error", "Cannot connect to server and set type.");
                    }
                });


            }
        });
    }

}



