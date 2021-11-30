package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.fragments.Buyerdashboardaccount;
import com.example.myapplication.fragments.Buyerdashboardhome;
import com.example.myapplication.fragments.Buyerdashboardorders;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.view.MenuItem;


public class BuyerDashboard extends AppCompatActivity {
    final Fragment fragH = new Buyerdashboardhome();
    final Fragment fragO = new Buyerdashboardorders();
    final Fragment fragA = new Buyerdashboardaccount();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_dashboard);

        BottomNavigationView navigation  = findViewById(R.id.buyerdashboard_nav);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        // add frag

        fm.beginTransaction().add(R.id.buyerframe,fragA,"A").hide(fragA).commit();
        fm.beginTransaction().add(R.id.buyerframe,fragO,"O").hide(fragO).commit();
        fm.beginTransaction().add(R.id.buyerframe,fragH,"H").commit();

    }

    private BottomNavigationView.OnItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationBarView.OnItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.buyerdashboard_navmenu_home:
                    fm.beginTransaction().hide(active).show(fragH).commit();
                    active = fragH;
                    return true;

                case R.id.buyerdashboard_navmenu_orders:
                    fm.beginTransaction().hide(active).show(fragO).commit();
                    active = fragO;
                    return true;

                case R.id.buyerdashboard_navmenu_account:
                    fm.beginTransaction().hide(active).show(fragA).commit();
                    active = fragA;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(BuyerDashboard.this, LoadingUserActivity.class);
        startActivity(intent);
    }

}