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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String Tag = "RecyclerViewAdapter";

    private ArrayList<String> mName ;
    private ArrayList<String> mBannersURL ;
    private ArrayList<String> mInfo;
    private ArrayList<String> eInfo;
    private Context mContext;
    private ArrayList<String> mId;
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> mId,ArrayList<String> mName, ArrayList<String> mBannersURL, ArrayList<String> mInfo,ArrayList<String> eInfo, Context mContext) {
    //public RecyclerViewAdapter(ArrayList<String> mName, ArrayList<String> mBannersURL, ArrayList<String> mInfo, Context mContext) {
        this.mName = mName;
        this.mBannersURL = mBannersURL;
        this.mInfo = mInfo;
        this.mContext = mContext;
        this.mId = mId;
        this.eInfo = eInfo;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mContext)
                .load(mBannersURL.get(position))
                .placeholder(R.drawable.cartlogo)
                .dontAnimate()
                .into(holder.banner);
        holder.name.setText(mName.get(position));
        holder.info.setText(mInfo.get(position));
        holder.btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PInfo.class);
                intent.putExtra("storeName", mName.get(position));
                intent.putExtra("storeBanner", mBannersURL.get(position));
                intent.putExtra("storeID", mId.get(position));
                intent.putExtra("eInfo", eInfo.get(position));
                context.startActivity(intent);
            }
        });


        /*
        // onclick btn send eInfo
        holder.parentStoreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, eInfo.get(position), Toast.LENGTH_LONG).show();

            }

        });
         */
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
        FloatingActionButton btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.sotrename);
            banner = itemView.findViewById(R.id.banner);
            info = itemView.findViewById(R.id.info);
            btn = itemView.findViewById(R.id.fabStore);
            parentStoreLayout = itemView.findViewById(R.id.parent_store_layout);
        }
    }
}
