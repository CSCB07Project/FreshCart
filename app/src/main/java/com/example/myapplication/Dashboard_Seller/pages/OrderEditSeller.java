package com.example.myapplication.Dashboard_Seller.pages;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.HashMap;

public class OrderEditSeller extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.seller_order_edit_layout, container, false);
        HashMap<String, Integer> products = (HashMap<String,Integer>)getArguments().getSerializable("products");
        String desc = "";
        for(String name: products.keySet()){
            desc += Character.toString((char) 183)+" "+ name + " x "+ products.get(name)+"\n\n";
        }
        //Remove last two newlines
        desc = desc.substring(0, desc.length()-2);


        TextView userName = v.findViewById(R.id.order_username);
        userName.setText(getArguments().getString("storeName"));

        TextView date = v.findViewById(R.id.seller_orderdate);
        date.setText(getArguments().getString("date"));
        TextView summary = v.findViewById(R.id.seller_order_summary);
        summary.setText(desc);
        Button delete_order = v.findViewById(R.id.seller_delete);
        Button confirm = v.findViewById(R.id.confirm_edit);
        Spinner spinner = v.findViewById(R.id.spinner);

        if(getArguments().getString("status").equals("-1")){
            confirm.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.INVISIBLE);
            delete_order.setVisibility(View.VISIBLE);
        }
        else{
            spinner.setSelection(Integer.valueOf(getArguments().getString("status")));
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = 0;
                if(spinner.getSelectedItem().toString().equals("Cancel")){
                    status = -1;
                }
                else if(spinner.getSelectedItem().toString().equals("Preparing Order")){
                    status = 1;
                }
                else if(spinner.getSelectedItem().toString().equals("Ready For Pickup")){
                    status = 2;
                }
                else if(spinner.getSelectedItem().toString().equals("Completed")){
                    status = 3;
                }
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("Orders").child(getArguments().getString("orderId")).child("status").setValue(status);
                dismiss();
            }
        });

        delete_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("Orders").child(getArguments().getString("orderId")).setValue(null);
            }
        });

        return v;
    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
}
