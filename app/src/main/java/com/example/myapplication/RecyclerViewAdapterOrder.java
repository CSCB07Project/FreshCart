package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.fragments.OrderEditUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewAdapterOrder extends FirebaseRecyclerAdapter<Order,RecyclerViewAdapterOrder.ViewHolderOrder> {
    private String desc = "";

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
        viewHolderOrder.price_items.setText("Price: $" + String.valueOf(order.getTotal_price()) + " " + Character.toString((char) 183)
                                            + " Items: " + String.valueOf(order.getProductIds().size()));
        if(order.getStatus() == 0){
            viewHolderOrder.status.setText(order.getDate() + " " + Character.toString((char) 183)+" Pending Approval");
        }
        else if(order.getStatus() == 1){
            viewHolderOrder.status.setText(order.getDate() + " " + Character.toString((char) 183)+" Preparing Order");
        }
        else if(order.getStatus() == 2){
            viewHolderOrder.status.setText(order.getDate() + " " + Character.toString((char) 183)+" Ready for Pickup");
        }
        else if(order.getStatus() == 3){
            viewHolderOrder.status.setText(order.getDate() + " " + Character.toString((char) 183)+" Completed");
        }
        else{
            viewHolderOrder.status.setText(order.getDate() + " " + Character.toString((char) 183)+" Cancelled");
        }
        viewHolderOrder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("storeName", order.getStore_name());
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                OrderEditUser test = new OrderEditUser();
                //Generate List of all products
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Products");
                ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            HashMap<String, Integer> products= new HashMap<String,  Integer>();
                            for(DataSnapshot d: task.getResult().getChildren()){
                                Product p = d.getValue(Product.class);
                                if(order.productids.containsKey(p.getProductID())){
                                    products.put(p.getProductName(), order.getProductIds().get(p.getProductID()));
                                }
                            }
                            bundle.putSerializable("products", products);
                            test.setArguments(bundle);
                            test.show(manager, "test");
                        }
                    }
                });



            }
        });

    }



    private interface FirebaseCallback{
        void onCallback(String d);

    }

    @NonNull
    @Override
    public ViewHolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);
        return new ViewHolderOrder(view);
    }
    public interface StringData {
        void StringData(String value);
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

