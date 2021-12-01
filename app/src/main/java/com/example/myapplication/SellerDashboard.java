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
import android.view.View;


public class SellerDashboard extends AppCompatActivity {
    Fragment fhome = new Sellerdashboardhome();
    Fragment faccount = new Sellerdashboardaccount();
    Fragment forders = new Sellerdashboardorders();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment curr = fhome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);

        BottomNavigationView bottomNavigation = findViewById(R.id.sellerdashboard_nav);
        //Set current frame

        fm.beginTransaction().add(R.id.sellerframe,faccount).hide(faccount).commit();
        fm.beginTransaction().add(R.id.sellerframe,forders).hide(forders).commit();
        fm.beginTransaction().add(R.id.sellerframe,fhome).commit();

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sellerdashboard_navmenu_home:
                        fm.beginTransaction().hide(curr).show(fhome).commit();
                        curr = fhome;
                        return true;

                    case R.id.sellerdashboard_navmenu_account:
                        fm.beginTransaction().hide(curr).show(faccount).commit();
                        curr = faccount;
                        return true;

                    case R.id.sellerdashboard_navmenu_orders:
                        fm.beginTransaction().hide(curr).show(forders).commit();
                        curr = forders;
                        return true;
                }
                return false;
            }
        });

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(SellerDashboard.this, LoadingUserActivity.class);
        startActivity(intent);
    }

    public void createStore(View view){
        Intent intent = new Intent(SellerDashboard.this, CreateNewStore.class);
        startActivity(intent);
    }

}