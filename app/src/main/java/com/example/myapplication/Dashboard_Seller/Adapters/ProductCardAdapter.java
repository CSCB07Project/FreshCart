package com.example.myapplication.Dashboard_Seller.Adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Product;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.example.myapplication.Store;



public class ProductCardAdapter extends FirebaseRecyclerAdapter<Product, ProductCardAdapter.ProductCard> {

    public ProductCardAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductCard holder, int position, @NonNull Product model) {
        holder.textViewProductName.setText(String.valueOf(model.getProductName()));
        holder.textViewCost.setText(String.valueOf(model.getProductPrice()));
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
        //ImageView ImageBanner;

        public ProductCard(View itemView){
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.productname_dashseller);
            textViewCost = itemView.findViewById(R.id.store_db_info_seller);
            //ImageBanner = itemView.findViewById(R.id.pd_banner_seller);
        }

    }
}
