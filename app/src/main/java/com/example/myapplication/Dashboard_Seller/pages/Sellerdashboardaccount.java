package com.example.myapplication.Dashboard_Seller.pages;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.LoadingUserActivity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Sellerdashboardaccount extends Fragment {
    private FirebaseAuth auth;
    private Button signout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sellerdashboardaccount, container, false);
        auth = FirebaseAuth.getInstance();
        signout = (Button) view.findViewById(R.id.signout_sellerdash);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();
            }
        });
        return view;
    }
    public void signout(){
        auth.signOut();
        Intent intent = new Intent(getActivity(), LoadingUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        FirebaseUser curr = FirebaseAuth.getInstance().getCurrentUser();
        if(curr == null){
            Intent intent = new Intent(getActivity(), LoadingUserActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
    }


}