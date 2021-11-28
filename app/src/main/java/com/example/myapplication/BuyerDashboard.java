package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.fragments.Buyerdashboardaccount;
import com.example.myapplication.fragments.Buyerdashboardhome;
import com.example.myapplication.fragments.Buyerdashboardorders;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.view.MenuItem;


public class BuyerDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_dashboard);

        BottomNavigationView bottomNavigation = findViewById(R.id.buyerdashboard_nav);

        //Set current frame
        getSupportFragmentManager().beginTransaction().replace(R.id.buyerframe, new Buyerdashboardhome()).commit();

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag1 = null;
                Fragment frag2 = null;
                Fragment frag3 = null;

                switch (item.getItemId()){
                    case R.id.buyerdashboard_navmenu_home:
                        if(frag1 == null)
                            frag1 = new Buyerdashboardhome();
                        getSupportFragmentManager().beginTransaction().replace(R.id.buyerframe, frag1).commit();
                        break;
                    case R.id.buyerdashboard_navmenu_account:
                        if(frag2 == null)
                            frag2 = new Buyerdashboardaccount();
                        getSupportFragmentManager().beginTransaction().replace(R.id.buyerframe, frag2).commit();
                        break;
                    case R.id.buyerdashboard_navmenu_orders:
                        if(frag3 == null)
                            frag3 = new Buyerdashboardorders();
                        getSupportFragmentManager().beginTransaction().replace(R.id.buyerframe, frag3).commit();
                        break;
                }
                //getSupportFragmentManager().beginTransaction().replace(R.id.buyerframe, selectedFragment).commit();
                return true;
            }
        });

    }




}