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

public class RecyclerViewAdapterOrder extends FirebaseRecyclerAdapter<Order,RecyclerViewAdapterOrder.ViewHolderOrder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private Context context;
    public RecyclerViewAdapterOrder(@NonNull FirebaseRecyclerOptions<Order> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderOrder viewHolderOrder, int i, @NonNull Order order) {
        viewHolderOrder.store_name.setText(order.getStore_name());
        viewHolderOrder.items.setText("Items: " + order.getProductIds().size());
        viewHolderOrder.price.setText("Price: $" + String.valueOf(order.getTotal_price()));
        if(order.getStatus() == 0){
            viewHolderOrder.status.setText("Pending Approval");
        }
        else if(order.getStatus() == 1){
            viewHolderOrder.status.setText("Preparing Order");
        }
        else if(order.getStatus() == 2){
            viewHolderOrder.status.setText("Ready");
        }
        else{
            viewHolderOrder.status.setText("Cancelled");
        }
    }

    @NonNull
    @Override
    public ViewHolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);
        return new ViewHolderOrder(view);
    }

    public class ViewHolderOrder extends RecyclerView.ViewHolder{

        ImageView background;
        TextView store_name;
        TextView items;
        TextView price;
        TextView status;
        ConstraintLayout parentLayout;

        public ViewHolderOrder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.background = itemView.findViewById(R.id.order_background);
            this.store_name = itemView.findViewById(R.id.userdash_order_store);
            this.items = itemView.findViewById(R.id.userdash_itemnum);
            this.price = itemView.findViewById(R.id.userdash_order_price);
            this.status = itemView.findViewById(R.id.userdash_order_status);
            this.parentLayout = itemView.findViewById(R.id.parent_order_layout);

        }
    }
}

