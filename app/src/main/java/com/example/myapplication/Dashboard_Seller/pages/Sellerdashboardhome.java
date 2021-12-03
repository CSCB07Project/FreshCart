package com.example.myapplication.Dashboard_Seller.pages;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.ChangeUsername;
import com.example.myapplication.Dashboard_Seller.Adapters.ProductCardAdapter;


import com.example.myapplication.Dashboard_Seller.CreateProduct;
import com.example.myapplication.Dashboard_Seller.SellerDashboard;
import com.example.myapplication.Product;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
        FloatingActionButton btn1 = (FloatingActionButton) myFragmentView.findViewById(R.id.floatingActionButtonAddProduct);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateProduct.class);
                startActivity(intent);

            }
        });
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