package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.fragments.Sellerdashboardaccount;
import com.example.myapplication.fragments.Sellerdashboardhome;
import com.example.myapplication.fragments.Sellerdashboardorders;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.view.MenuItem;


public class SellerDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);

        BottomNavigationView bottomNavigation = findViewById(R.id.sellerdashboard_nav);

        //Set current frame
        getSupportFragmentManager().beginTransaction().replace(R.id.sellerframe, new Sellerdashboardhome()).commit();

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.sellerdashboard_navmenu_home:
                        selectedFragment = new Sellerdashboardhome();
                        break;
                    case R.id.sellerdashboard_navmenu_account:
                        selectedFragment = new Sellerdashboardaccount();
                        break;
                    case R.id.sellerdashboard_navmenu_orders:
                        selectedFragment = new Sellerdashboardorders();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.sellerframe, selectedFragment).commit();
                return true;
            }
        });

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(SellerDashboard.this, LoadingUserActivity.class);
        startActivity(intent);
    }
}