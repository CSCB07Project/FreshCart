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

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapter;
import com.example.myapplication.Store;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sellerdashboardhome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buyerdashboardhome extends Fragment {

    //private ArrayList<String> mBannerUrl = new ArrayList<>();
    //private ArrayList<String> mInfo = new ArrayList<>();
    //private ArrayList<String> eInfo = new ArrayList<>();
    //private ArrayList<String> mName = new ArrayList<>();
    //private ArrayList<String> mId = new ArrayList<>();



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    // TODO: Rename and change types of parameters
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

        /*
        DatabaseReference ref1 =  FirebaseDatabase.getInstance().getReference("Seller");
       ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot item : snapshot.getChildren()) {
                    String storeName = "", storeBannerUrl = "", storeAddress = "";
                    String storeContact = "", storeDescription = "", storeCountry = "", storeProvince = "", storeCity = "", storePostal = "";
                    String storeID = "";
                    if (item.hasChild("storeName"))
                        storeName = item.child("storeName").getValue().toString();
                    if (item.hasChild("storeAddress"))
                        storeAddress = item.child("storeAddress").getValue().toString();
                    if (item.hasChild("storeBannerUrl"))
                        storeBannerUrl = item.child("storeBannerUrl").getValue().toString();
                    if (item.hasChild("storeID"))
                        storeID = item.child("storeID").getValue().toString();

                    if (item.hasChild("storeContact"))
                        storeContact = item.child("storeContact").getValue().toString();

                    if (item.hasChild("storeDescription"))
                        storeDescription = item.child("storeDescription").getValue().toString();
                    if (item.hasChild("storeCountry"))
                        storeCountry = item.child("storeCountry").getValue().toString();
                    if (item.hasChild("storeProvince"))
                        storeProvince = item.child("storeProvince").getValue().toString();
                    if (item.hasChild("storeCity"))
                        storeCity = item.child("storeCity").getValue().toString();
                    if (item.hasChild("storePostal"))
                        storePostal = item.child("storePostal").getValue().toString();

                    String xInfo =
                            "Store ID:                   " + storeID +
                                    "\nStore Name:            " + storeName +
                                    "\nStore Description:   " + storeDescription +
                                    "\nStore Contact:         " + storeContact +
                                    "\nStore Address:        " + storeAddress +
                                    "\nStore Postal:            " + storePostal +
                                    "\nStore City:                " + storeCity +
                                    "\nStore Province:       " + storeProvince +
                                    "\nStore Country:         " + storeCountry;
                     eInfo.add(xInfo);

                    mId.add(storeID);
                    mName.add("  " + storeName);
                    mInfo.add("    " + storeAddress);
                    mBannerUrl.add(storeBannerUrl);

                }
                //ref1.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Doesnt work", "Doesnt work");
            }
        });

         */

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
        //adapter = new RecyclerViewAdapter(mId, mName,mBannerUrl, mInfo,eInfo, getActivity());
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
    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}