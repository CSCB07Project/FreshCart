package com.example.myapplication.Dashboard_Seller.Adapters;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.*;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class StoreCardAdapter extends FirebaseRecyclerAdapter<Store, StoreCardAdapter.StoreCard>{


    public StoreCardAdapter(@NonNull FirebaseRecyclerOptions<Store> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull StoreCard holder, int position, @NonNull Store model) {
        holder.textViewStoreName.setText(String.valueOf(model.getStoreName()));
        holder.textViewLocation.setText(String.valueOf(model.getStoreAddress()));
        //aDD IMAGE

        //Add code to view image.

    }

    @NonNull
    @Override
    public StoreCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list,parent,false);
        return new StoreCard(v);
    }

    class StoreCard extends RecyclerView.ViewHolder{
        TextView textViewStoreName;
        TextView textViewLocation;
        ImageView ImageBanner;

        public StoreCard(View itemView){
            super(itemView);
            textViewStoreName = itemView.findViewById(R.id.storename_dash);
            textViewLocation = itemView.findViewById(R.id.store_db_info);
            ImageBanner = itemView.findViewById(R.id.db_banner);
        }

    }
}
