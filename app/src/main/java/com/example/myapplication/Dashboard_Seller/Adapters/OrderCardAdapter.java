package com.example.myapplication.Dashboard_Seller.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Buyer;
import com.example.myapplication.Dashboard_Seller.pages.OrderEditSeller;
import com.example.myapplication.Order;
import com.example.myapplication.Product;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapterOrder;
import com.example.myapplication.fragments.OrderEditUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OrderCardAdapter extends FirebaseRecyclerAdapter<Order,OrderCardAdapter.SellerOrderCard> {
    private Context context;
    public OrderCardAdapter(@NonNull FirebaseRecyclerOptions<Order> options){
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull SellerOrderCard holder, int position, @NonNull Order model){

        DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference("Users");
        user_ref.child(model.getUserid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Buyer found = task.getResult().getValue(Buyer.class);
                    holder.store_name.setText(found.getUsername());

                }
                else{
                    Log.d("Failed", "Task Failed");
                }
            }
        });
        holder.price_items.setText("Price: $" + String.valueOf(model.getTotal_price()) + " " + Character.toString((char) 183)
                + " Items: " + String.valueOf(model.getProductIds().size()));
        if(model.getStatus() == 0){
            holder.status.setText(model.getDate() + " " + Character.toString((char) 183)+" Pending Approval");
        }
        else if(model.getStatus() == 1){
            holder.status.setText(model.getDate() + " " + Character.toString((char) 183)+" Preparing Order");
        }
        else if(model.getStatus() == 2){
            holder.status.setText(model.getDate() + " " + Character.toString((char) 183)+" Ready for Pickup");
        }
        else if(model.getStatus() == 3){
            holder.status.setText(model.getDate() + " " + Character.toString((char) 183)+" Completed");
        }
        else{
            holder.status.setText(model.getDate() + " " + Character.toString((char) 183)+" Cancelled");
        }

        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("storeName", model.getStore_name());
                bundle.putString("orderId", model.getOrderid());
                bundle.putString("status", String.valueOf(model.getStatus()));
                bundle.putString("date", model.getDate());
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                OrderEditSeller seller_bottomsheet = new OrderEditSeller();
                //Generate List of all products
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Products");
                ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            HashMap<String, Integer> products= new HashMap<String,  Integer>();
                            for(DataSnapshot d: task.getResult().getChildren()){
                                Product p = d.getValue(Product.class);
                                if(model.getProductIds().containsKey(p.getProductID())){
                                    products.put(p.getProductName(), model.getProductIds().get(p.getProductID()));
                                }
                            }
                            bundle.putSerializable("products", products);
                            seller_bottomsheet.setArguments(bundle);
                            seller_bottomsheet.show(manager, "test");
                        }
                    }
                });



            }
        });

    }

    @NonNull
    @Override
    public SellerOrderCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);
        return new SellerOrderCard(view);
    }

    public class SellerOrderCard extends RecyclerView.ViewHolder{
        ImageView background;
        TextView store_name;
        TextView price_items;
        TextView status;
        ConstraintLayout parentLayout;
        public SellerOrderCard(@NonNull View itemView) {
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
