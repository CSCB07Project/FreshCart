package com.example.myapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
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
        TextView tv = v.findViewById(R.id.orderIdTest);
        tv.setText(getArguments().getString("storeName"));
        TextView summary = v.findViewById(R.id.order_summary);
        summary.setText(desc);
        return v;
    }

}
