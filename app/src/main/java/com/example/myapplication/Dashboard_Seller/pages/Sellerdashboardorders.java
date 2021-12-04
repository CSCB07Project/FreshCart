package com.example.myapplication.Dashboard_Seller.pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Dashboard_Seller.Adapters.OrderCardAdapter;
import com.example.myapplication.Order;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapterOrder;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Sellerdashboardorders extends Fragment {
    RecyclerView recyclerView;
    OrderCardAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyerdashboardorders, container, false);
        recyclerView = view.findViewById(R.id.userdashorders_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseRecyclerOptions<Order> options =
                new FirebaseRecyclerOptions.Builder<Order>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Orders").orderByChild("storeid").equalTo(getArguments().getString("udid")), Order.class)
                        .build();
        adapter = new OrderCardAdapter(options);
        recyclerView.setAdapter(adapter);
        //TextView
        adapter.startListening();
        adapter.notifyDataSetChanged(); // Added This line
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}