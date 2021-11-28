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
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.buyerdashboard_navmenu_home:
                        selectedFragment = new Buyerdashboardhome();
                        break;
                    case R.id.buyerdashboard_navmenu_account:
                        selectedFragment = new Buyerdashboardaccount();
                        break;
                    case R.id.buyerdashboard_navmenu_orders:
                        selectedFragment = new Buyerdashboardorders();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.buyerframe, selectedFragment).commit();
                return true;
            }
        });

    }




}