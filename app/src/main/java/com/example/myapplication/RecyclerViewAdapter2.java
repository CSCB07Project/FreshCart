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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

import java.util.*;

public class RecyclerViewAdapter2 extends FirebaseRecyclerAdapter<Product,RecyclerViewAdapter2.ViewHolder2> {

    private static final String TAG = "RecyclerViewAdapter2";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter2(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }


    @NonNull
    @Override
    public RecyclerViewAdapter2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_items2, parent, false);
        ViewHolder2 holder = new ViewHolder2(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder2 holder, int position, @NonNull Product model) {
        holder.name.setText(model.getProductName());
        holder.price.setText("$ " + model.productPrice);
        holder.desc.setText(model.getProductDescription());

        Glide.with(mContext)
                .asBitmap()
                .load(model.productImageUrl)
                .into(holder.image);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingleProductPage.class);
                intent.putExtra("mImageNames", model.productName);
                intent.putExtra("mPrice", model.productPrice);
                intent.putExtra("mImages", model.productImageUrl);
                intent.putExtra("mDesc", model.productDescription);
                intent.putExtra("mId",model.productID);
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
        ConstraintLayout parentLayout;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productimage);
            name = itemView.findViewById(R.id.productname);
            price = itemView.findViewById(R.id.productprice);
            desc = itemView.findViewById(R.id.productdesc);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
