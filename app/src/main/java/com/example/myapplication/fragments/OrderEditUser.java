package com.example.myapplication.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OrderEditUser extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_order_edit_layout, container, false);
        HashMap<String, Integer> products = (HashMap<String,Integer>)getArguments().getSerializable("products");
        String desc = "";
        for(String name: products.keySet()){
            desc += Character.toString((char) 183)+" "+ name + " x "+ products.get(name)+"\n\n";
        }

        TextView storeName = v.findViewById(R.id.orderIdTest);
        storeName.setText(getArguments().getString("storeName"));

        TextView date = v.findViewById(R.id.user_order_date);
        date.setText(getArguments().getString("date"));

        TextView summary = v.findViewById(R.id.user_order_summary);
        summary.setText(desc);
        Button cancel = v.findViewById(R.id.user_cancel_order);
        TextView status = v.findViewById(R.id.user_order_status);
        if(getArguments().getString("status").equals("-1")){
            status.setTextColor(Color.RED);
            status.setText("Cancelled");
            cancel.setText("Delete Order");
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    ref.child("Orders").child(getArguments().getString("orderId")).setValue(null);
                }
            });
        }else if(getArguments().getString("status").equals("0")){
            status.setTextColor(Color.YELLOW);
            status.setText("Pending Approval");
        }else if(getArguments().getString("status").equals("1")){
            status.setTextColor(Color.BLUE);
            status.setText("Preparing Order");
        }else if(getArguments().getString("status").equals("2")){
            status.setTextColor(Color.GREEN);
            status.setText("Ready For Pickup");
        }else{
            status.setTextColor(Color.BLACK);
            status.setText("Completed");
        }


        if(getArguments().getString("status") != "-1"){
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    ref.child("Orders").child(getArguments().getString("orderId")).child("status").setValue(-1);
                }
            });
        }

        return v;
    }

}
