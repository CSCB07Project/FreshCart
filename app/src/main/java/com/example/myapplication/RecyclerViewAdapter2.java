package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder2>{

    private static final String TAG = "RecyclerViewAdapter2";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();
    private ArrayList<String> mstoreName = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter2(ArrayList<String> ImageNames,ArrayList<String> Images, ArrayList<String> Price, ArrayList<String> Desc, ArrayList<String> storeName, Context Context) {
        this.mImageNames = ImageNames;
        this.mImages = Images;
        this.mPrice = Price;
        this.mContext = Context;
        this.mDesc = Desc;
        this.mstoreName = storeName;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_items2, parent, false);
        ViewHolder2 holder = new ViewHolder2(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.name.setText(mImageNames.get(position));
        holder.price.setText("$ " + mPrice.get(position));
        holder.desc.setText(mDesc.get(position));
        holder.storeN.setText(mstoreName.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingleProductPage.class);
                intent.putExtra("mImageNames", mImageNames.get(position));
                intent.putExtra("mPrice", mPrice.get(position));
                intent.putExtra("mImages", mImages.get(position));
                intent.putExtra("mDesc", mDesc.get(position));
                intent.putExtra("mstoreName", mstoreName.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView price;
        TextView desc;
        TextView storeN;
        ConstraintLayout parentLayout;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productimage);
            name = itemView.findViewById(R.id.storename);
            price = itemView.findViewById(R.id.productprice);
            desc = itemView.findViewById(R.id.productdesc);
            storeN = itemView.findViewById(R.id.storeNN);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
