package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.ChangeEmail;
import com.example.myapplication.ChangeName;
import com.example.myapplication.ChangePassword;
import com.example.myapplication.ChangeUsername;
import com.example.myapplication.LoadingUserActivity;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sellerdashboardaccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buyerdashboardaccount extends Fragment {
    private FirebaseAuth auth;
    private Button signout_button;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Buyerdashboardaccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sellerdashboardaccount.
     */
    // TODO: Rename and change types and number of parameters
    public static Buyerdashboardaccount newInstance(String param1, String param2) {
        Buyerdashboardaccount fragment = new Buyerdashboardaccount();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        auth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_buyerdashboardaccount, container, false);
        signout_button = (Button) view.findViewById(R.id.signout_userdash);
        signout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();
            }
        });

        FloatingActionButton btn1 = (FloatingActionButton) view.findViewById(R.id.bUsername);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeUsername.class);
                startActivity(intent);

            }
        });

        FloatingActionButton btn2 = (FloatingActionButton) view.findViewById(R.id.bName);

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeName.class);
                startActivity(intent);

            }
        });

        FloatingActionButton btn3 = (FloatingActionButton) view.findViewById(R.id.bEM);

        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeEmail.class);
                startActivity(intent);

            }
        });
        FloatingActionButton btn4 = (FloatingActionButton) view.findViewById(R.id.bPS);

        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);

            }
        });
        return view;
    }



    public void signout(){
        auth.signOut();
        Intent intent = new Intent(getActivity(), LoadingUserActivity.class);

        startActivity(intent);
    }



}