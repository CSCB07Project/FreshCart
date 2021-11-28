package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapter;
import com.example.myapplication.Store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sellerdashboardhome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buyerdashboardhome extends Fragment {

    private View myFragmentView;

    private ArrayList<String> mBannerUrl;
    private ArrayList<String> mInfo = new ArrayList<>();
    private ArrayList<Store> stores;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Buyerdashboardhome() {
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
    public static Buyerdashboardhome newInstance(String param1, String param2) {
        Buyerdashboardhome fragment = new Buyerdashboardhome();
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

    private void initRecyclerView(){
        RecyclerView recyclerView = myFragmentView.findViewById(R.id.recyclerViewBuyer);
        //RecyclerViewAdapter adapter = new RecyclerViewAdapter(mBannerUrl, mInfo, getActivity());
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( mInfo, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragmentView =  inflater.inflate(R.layout.fragment_buyerdashboardhome, container, false);

        mInfo.add("1");
        mInfo.add("2");
        mInfo.add("3");
        mInfo.add("4");
        mInfo.add("5");
        initRecyclerView();
        // Inflate the layout for this fragment
        return myFragmentView;
    }
}