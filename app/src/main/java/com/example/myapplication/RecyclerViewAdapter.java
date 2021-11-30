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

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String Tag = "RecyclerViewAdapter";

    private ArrayList<String> mName ;
    private ArrayList<String> mBannersURL ;
    private ArrayList<String> mInfo;
    private Context mContext;
    private ArrayList<String> eInfo;

    //public RecyclerViewAdapter(ArrayList<String> eInfo,ArrayList<String> mName, ArrayList<String> mBannersURL, ArrayList<String> mInfo, Context mContext) {
    public RecyclerViewAdapter(ArrayList<String> mName, ArrayList<String> mBannersURL, ArrayList<String> mInfo, Context mContext) {
    //public RecyclerViewAdapter( ArrayList<String> mInfo, Context mContext) {
        this.mName = mName;
        this.mBannersURL = mBannersURL;
        this.mInfo = mInfo;
        this.mContext = mContext;
        //this.eInfo = eInfo;
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
        Glide.with(mContext)
                .load(mBannersURL.get(position))
                .placeholder(R.drawable.cartlogo)
                .dontAnimate()
                .into(holder.banner);
        //holder.banner.setText(mBannersURL.get(position));
        holder.name.setText(mName.get(position));
        holder.info.setText(mInfo.get(position));
        // onclick btn send eInfo
    }

    @Override
    public int getItemCount() {
        return mInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView banner;
        TextView name;
        TextView info;
        ConstraintLayout parentStoreLayout;
        //FloatingActionButton btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sotrename);
            banner = itemView.findViewById(R.id.banner);
            info = itemView.findViewById(R.id.info);
            //btn = itemView.findViewById(R.id.fabStore);
            parentStoreLayout = itemView.findViewById(R.id.parent_store_layout);
        }
    }
}
