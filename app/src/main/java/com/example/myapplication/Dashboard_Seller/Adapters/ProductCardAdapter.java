package com.example.myapplication.Dashboard_Seller.Adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Dashboard_Seller.EditDeleteProduct;
import com.example.myapplication.Dashboard_Seller.SellerDashboard;
import com.example.myapplication.Product;
import com.example.myapplication.R;
import com.example.myapplication.SingleProductPage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.example.myapplication.Store;



public class ProductCardAdapter extends FirebaseRecyclerAdapter<Product, ProductCardAdapter.ProductCard> {
    private Context context;

    public ProductCardAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull ProductCard holder, int position, @NonNull Product model) {
        //Set Content
        holder.textViewProductName.setText(String.valueOf(model.getProductName()));
        holder.textViewCost.setText(String.valueOf(model.getProductPrice()));
        Glide.with(context)
                .load(model.getProductImageUrl())
                .placeholder(R.drawable.cartlogo)
                .dontAnimate()
                .into(holder.ImageBanner);

        //Add new Onclick
        holder.cardlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditDeleteProduct.class);
                //Product Information.
                intent.putExtra("productName",model.getProductName());
                intent.putExtra("productDescription",model.getProductDescription());
                intent.putExtra("productPrice",Float.toString(model.getProductPrice()));
                intent.putExtra("productURL",model.getProductImageUrl());
                intent.putExtra("productStore",model.getStore());
                intent.putExtra("productID",model.getProductID());

                context.startActivity(intent);
            }
        });

    }


    @NonNull
    @Override
    public ProductCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);
        return new ProductCard(v);
    }

    class ProductCard extends RecyclerView.ViewHolder{
        TextView textViewProductName;
        TextView textViewCost;
        ImageView ImageBanner;
        CardView cardlayout;

        public ProductCard(View itemView){
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.productname_dashseller);
            textViewCost = itemView.findViewById(R.id.store_db_info_seller);
            ImageBanner = itemView.findViewById(R.id.pd_banner_seller);
            cardlayout = itemView.findViewById(R.id.cardView_store);
            context = itemView.getContext();
        }

    }
}
