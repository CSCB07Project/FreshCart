package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.myapplication.CreateNewStore;
import com.example.myapplication.LoadingUserActivity;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sellerdashboardhome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sellerdashboardhome extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference data;

    private View myFragmentView;

    //Information
    String BannerUrl = "";
    String mName = "";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sellerdashboardhome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sellerdashboardhome.
     */
    // TODO: Rename and change types and number of parameters
    public static Sellerdashboardhome newInstance(String param1, String param2) {
        Sellerdashboardhome fragment = new Sellerdashboardhome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }


    public void setViewType(){
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid().toString();

        FloatingActionButton newStoreBtn = myFragmentView.findViewById(R.id.addNewStoreItem);
        TextView Stat = myFragmentView.findViewById(R.id.AddStoreText);

        data = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                String obtained;
                try{
                    obtained = snapshot.child("storeID").getValue().toString();
                    if(obtained == null || obtained.compareTo("-1") == 0){
                        //add option to create new user.
                        newStoreBtn.setVisibility(View.VISIBLE);
                        myFragmentView.setVisibility(View.VISIBLE);
                    }else{
                        newStoreBtn.setVisibility(View.INVISIBLE);
                        myFragmentView.setVisibility(View.INVISIBLE);

                    }

                }catch(Exception e){
                    FirebaseAuth.getInstance().signOut();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Cannot connect to server and set type.");
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //UI ELEMENTS

        myFragmentView =  inflater.inflate(R.layout.fragment_sellerdashboardhome, container, false);
        setViewType();

        //Add adapter to display the store Owners ITEMS.

        //CODE ^^

        return myFragmentView;
    }




}