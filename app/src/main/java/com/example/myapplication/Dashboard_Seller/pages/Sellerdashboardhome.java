package com.example.myapplication.Dashboard_Seller.pages;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapplication.Dashboard_Seller.Adapters.StoreCardAdapter;
import com.example.myapplication.Dashboard_Seller.models.StoreReaderWriter;
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

    //Store Information
    private Store userStore;
    private StoreReaderWriter op;
    private FirebaseAuth mAuth;

    //UI BINDERS



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        op = new StoreReaderWriter();
        mAuth = FirebaseAuth.getInstance();
        userStore = op.fetchLatest(mAuth.getUid().toString());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView =  inflater.inflate(R.layout.fragment_sellerdashboardhome, container, false);
        return myFragmentView;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }




}