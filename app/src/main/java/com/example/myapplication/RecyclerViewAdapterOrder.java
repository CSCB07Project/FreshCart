package com.example.myapplication;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.HashMap;

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
        HashMap<String, Integer> test= order.getProductIds();
        Log.d("TESTINGTETSINGTESTING", String.valueOf(test.get("id1")));
        viewHolderOrder.price_items.setText("Price: $" + String.valueOf(order.getTotal_price()) + " " + Character.toString((char) 183)
                                            + " Items: " + String.valueOf(order.getProductIds().size()));
        if(order.getStatus() == 0){
            viewHolderOrder.status.setText(order.getDate() + " " + Character.toString((char) 183)+" Pending Approval");
        }
        else if(order.getStatus() == 1){
            viewHolderOrder.status.setText(order.getDate() + " " + Character.toString((char) 183)+" Preparing Order");
        }
        else if(order.getStatus() == 2){
            viewHolderOrder.status.setText(order.getDate() + " " + Character.toString((char) 183)+" Ready");
        }
        else{
            viewHolderOrder.status.setText(order.getDate() + " " + Character.toString((char) 183)+" Cancelled");
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
        TextView price_items;
        TextView status;
        ConstraintLayout parentLayout;

        public ViewHolderOrder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.background = itemView.findViewById(R.id.order_background);
            this.store_name = itemView.findViewById(R.id.userdash_order_store);
            this.price_items = itemView.findViewById(R.id.userdash_order_price_items);
            this.status = itemView.findViewById(R.id.userdash_order_status);
            this.parentLayout = itemView.findViewById(R.id.parent_order_layout);
        }
    }
}

