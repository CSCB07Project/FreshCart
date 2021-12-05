package com.example.myapplication.Dashboard_Seller.pages;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.ChangeUsername;
import com.example.myapplication.Dashboard_Seller.Adapters.ProductCardAdapter;


import com.example.myapplication.Dashboard_Seller.CreateProduct;
import com.example.myapplication.Dashboard_Seller.SellerDashboard;
import com.example.myapplication.Dashboard_Seller.models.SellerAccountReaderWriter;
import com.example.myapplication.Dashboard_Seller.models.StoreReaderWriter;
import com.example.myapplication.Product;
import com.example.myapplication.R;
import com.example.myapplication.Seller;
import com.example.myapplication.Store;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ValueEventListener;


public class Sellerdashboardhome extends Fragment {
    private View myFragmentView;
    private ProductCardAdapter adapter;
    private FirebaseAuth mAuth;
    private DatabaseReference data;
    private String StoreUUID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }


    private void recycle_view_starer(){

        Query query = FirebaseDatabase.getInstance().getReference("Products").orderByChild("store").equalTo(StoreUUID);


        FirebaseRecyclerOptions<Product> cards = new FirebaseRecyclerOptions.Builder<Product>().setQuery(query,Product.class).build();
        adapter = new ProductCardAdapter(cards);
        RecyclerView recyclerView = myFragmentView.findViewById(R.id.store_produce_recycler_container);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


    }

    public void load_status(View myFragmentView, String uid){
        data = FirebaseDatabase.getInstance().getReference().child("Store").child(uid);
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                String StoreName = "Store Seller";
                String bannerUrl = "";
                try{
                    StoreName = snapshot.child("storeName").getValue().toString();
                    bannerUrl = snapshot.child("storeBannerUrl").getValue().toString();
                    if(!(StoreName == null || StoreName.compareTo("-1") == 0)){
                        CollapsingToolbarLayout bar = myFragmentView.findViewById(R.id.StoreTitleBar);
                        bar.setTitle(StoreName);

                        ImageView img = myFragmentView.findViewById(R.id.banner_img);

                        Glide.with(getContext())
                                .load(bannerUrl)
                                .placeholder(R.drawable.cartlogo)
                                .dontAnimate()
                                .into(img);


                        //bar.setBackground();
                    }
                }catch(Exception e){
                    FirebaseAuth.getInstance().signOut();
              }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Cannot connect to server and set type.");
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView =  inflater.inflate(R.layout.fragment_sellerdashboardhome, container, false);

        recycle_view_starer();
        return myFragmentView;
    }

    public void AddProduct(View view){
        Intent intent = new Intent(getActivity(), CreateProduct.class);
        intent.putExtra("uuid", StoreUUID);
        startActivity(intent);
    }

    @Override
    public void onStart(){
        super.onStart();
        StoreUUID  = getArguments().getString("udid");
        load_status(myFragmentView, StoreUUID);
        adapter.startListening();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume(){
        super.onResume();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}