package com.example.myapplication.Dashboard_Seller.pages;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.myapplication.Dashboard_Seller.Adapters.ProductCardAdapter;


import com.example.myapplication.Product;
import com.example.myapplication.R;
import com.example.myapplication.Store;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class Sellerdashboardhome extends Fragment {
    private View myFragmentView;
    private ProductCardAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void recycle_view_starer(){
        Query query = FirebaseDatabase.getInstance().getReference("Products");

        FirebaseRecyclerOptions<Product> cards = new FirebaseRecyclerOptions.Builder<Product>().setQuery(query,Product.class).build();
        adapter = new ProductCardAdapter(cards);
        RecyclerView recyclerView = myFragmentView.findViewById(R.id.store_produce_recycler_container);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView =  inflater.inflate(R.layout.fragment_sellerdashboardhome, container, false);
        Log.e("ERror", "RAN MULTIPLE TIMES");
        recycle_view_starer();
        return myFragmentView;
    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
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