package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String Tag = "RecyclerViewAdapter";

    //private ArrayList<String> mBannersURL ;
    private ArrayList<String> mInfo;
    private Context mContext;

    //public RecyclerViewAdapter(ArrayList<String> mBannersURL, ArrayList<String> mInfo, Context mContext) {
    public RecyclerViewAdapter( ArrayList<String> mInfo, Context mContext) {
        //this.mBannersURL = mBannersURL;
        this.mInfo = mInfo;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Glide.with(mContext).asBitmap().load(mBannersURL.get(position)).into(holder.banner);
        //holder.banner.setText(mBannersURL.get(position));

        holder.info.setText(mInfo.get(position));
    }

    @Override
    public int getItemCount() {
        return mInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //ImageView banner;
        //TextView banner;
        TextView info;
        ConstraintLayout parentStoreLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //banner = itemView.findViewById(R.id.banner);
            info = itemView.findViewById(R.id.info);
            parentStoreLayout = itemView.findViewById(R.id.parent_store_layout);
        }
    }
}
