package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Cart extends AppCompatActivity {
    ArrayList<String> mCount = new ArrayList<>();
    ArrayList<String> mID = new ArrayList<>();
    ArrayList<String> mName = new ArrayList<>();
    float total_price = 0;
    RecyclerViewAdapterCart adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_cart);

        String storeName = "";
        String storeID = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            storeName = extras.getString("storeName");
            storeID = extras.getString("storeID");
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Button btn = (Button) findViewById(R.id.ppOrder);
        String finalStoreName = storeName;
        String finalStoreID = storeID;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mID.size()==0) Toast.makeText(Cart.this, "Cart is Empty", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Cart.this, "Order Placed", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onClick: ");
                    String orderId = UUID.randomUUID().toString().replaceAll("-", "");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String date = formatter.format(new Date());
                    Order newOrder = new Order(orderId, finalStoreID, uid, 0, total_price, finalStoreName, date);
                    for(int i = 0; i<mID.size();i++){
                        newOrder.addProduct(mID.get(i),Integer.parseInt(mCount.get(i)));
                    }
                    // write to orders
                    FirebaseDatabase.getInstance().getReference("Orders").child(orderId).setValue(newOrder);

                    // write to buyer
                    //FirebaseDatabase.getInstance().getReference("Users").child(uid).child("buyerOrders").child(orderId).setValue(orderId);

                    //write to seller

                    /*
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                    Query q = ref.orderByChild("storeID").equalTo(finalStoreID);
                    q.addValueEventListener( new ValueEventListener(){
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren() ){
                                String key = ds.getKey();
                                Log.d("TAG", key);
                                ref.child(key).child("sellerOrders").child(orderId).setValue(orderId);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });

                     */

                    // reset buyer cart
                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child("cart").removeValue();

                    Intent intent = new Intent(Cart.this, PInfo.class);
                    startActivity(intent);
                }
            }

        });

        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ss : snapshot.getChildren()){
                    if(!ss.getKey().toString().equals("-1")){
                        mID.add(ss.getKey().toString());
                        mCount.add(ss.getValue().toString());
                    }
                    Log.d("TAG", ss.toString());
                }
                Log.d("TAG", mID.toString());
                Log.d("TAG", mCount.toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        FirebaseDatabase.getInstance().getReference("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Integer> indexRemove = new ArrayList<>();
                int offset = 0;
                for(String id: mID){
                    if(snapshot.child(id).child("productName").getValue() != null){
                        String productName = snapshot.child(id).child("productName").getValue().toString();
                        total_price += Float.parseFloat(snapshot.child(id).child("productPrice").getValue().toString());
                        mName.add(productName);
                        Log.d("productName", productName);
                    }else {
                        indexRemove.add(mID.indexOf(id));
                        Log.d("del", id);
                        Log.d("del", String.valueOf(mID.indexOf(id)));
                    }
                }
                Log.d("TAG", indexRemove.toString());
                for(int i: indexRemove){
                    mID.remove(i - offset);
                    mCount.remove(i - offset);
                    offset++;
                }
                adapter = new RecyclerViewAdapterCart(mID, mName, mCount, Cart.this);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCartz);
                recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
