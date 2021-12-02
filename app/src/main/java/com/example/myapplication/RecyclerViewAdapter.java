package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerViewAdapter extends FirebaseRecyclerAdapter<Store,RecyclerViewAdapter.ViewHolder> {
    private Context context;

    public RecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<Store> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Store store) {
        viewHolder.name.setText(store.getStoreName());
        viewHolder.info.setText(store.getStoreAddress());
        Glide.with(context)
                .load(store.getStoreBannerUrl())
                .placeholder(R.drawable.cartlogo)
                .dontAnimate()
                .into(viewHolder.banner);

        viewHolder.parentStoreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PInfo.class);
                intent.putExtra("storeName", store.getStoreName());
                intent.putExtra("storeBanner", store.getStoreBannerUrl());
                intent.putExtra("storeID",store.getStoreID());
                String eInfo = "Store ID:                   " + store.getStoreID() +
                        "\nStore Name:            " + store.getStoreName() +
                        "\nStore Description:   " + store.getStoreDescription() +
                        "\nStore Contact:         " + store.getStoreContact() +
                        "\nStore Address:        " + store.getStoreAddress() +
                        "\nStore Postal:            " + store.getStorePostal() +
                        "\nStore City:                " + store.getStoreCity() +
                        "\nStore Province:       " + store.getStoreProvince() +
                        "\nStore Country:         " + store.getStoreCountry();
                intent.putExtra("eInfo", eInfo);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_items, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView banner;
        TextView name;
        TextView info;
        ConstraintLayout parentStoreLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.sotrename);
            banner = itemView.findViewById(R.id.banner);
            info = itemView.findViewById(R.id.info);
            parentStoreLayout = itemView.findViewById(R.id.parent_store_layout);
        }
    }
}
