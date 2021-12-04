package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.*;

public class RecyclerViewAdapterCart extends RecyclerView.Adapter<RecyclerViewAdapterCart.ViewHolderCart>{

    private ArrayList<String> mCount;
    private ArrayList<String > mID;
    ArrayList<String> mName;
    private Context mContext;

    public RecyclerViewAdapterCart(ArrayList<String> mID,ArrayList<String> mName, ArrayList<String> mCount, Context mContext) {
        this.mCount = mCount;
        this.mID = mID;
        this.mName = mName;
        this.mContext = mContext;
        Log.d("TAG", String.valueOf(mCount.size()));
        Log.d("TAG", mCount.toString());
        Log.d("TAG", mID.toString());

    }

    @NonNull
    @Override
    public RecyclerViewAdapterCart.ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list, parent, false);
        return new ViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCart holder, @SuppressLint("RecyclerView") final int position) {
        holder.name.setText(mName.get(position));
        holder.count.setText(mCount.get(position));
    }

    @Override
    public int getItemCount() {
        return mID.size();
    }

    public class ViewHolderCart extends RecyclerView.ViewHolder{

        TextView name;
        TextView count;
        ConstraintLayout parentLayout;

        public ViewHolderCart(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cartname);
            count = itemView.findViewById(R.id.cartamount);
            parentLayout = itemView.findViewById(R.id.parent_layout_cart);
        }
    }
}
