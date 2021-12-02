package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Dashboard_Seller.pages.Sellerdashboardhome;
import com.example.myapplication.LoadingUserActivity;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapter;
import com.example.myapplication.Store;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;

public class Buyerdashboardhome extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    private String mParam1;
    private String mParam2;

    public Buyerdashboardhome() {
        // Required empty public constructor
    }


    public static Buyerdashboardhome newInstance(String param1, String param2) {
        Buyerdashboardhome fragment = new Buyerdashboardhome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_buyerdashboardhome, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBuyer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Store> options =
                new FirebaseRecyclerOptions.Builder<Store>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Seller"), Store.class)
                        .build();
        adapter = new RecyclerViewAdapter(options);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        FirebaseUser curr = FirebaseAuth.getInstance().getCurrentUser();
        if(curr == null){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}