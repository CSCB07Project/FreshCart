package com.example.myapplication.Dashboard_Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Dashboard_Seller.pages.Sellerdashboardaccount;
import com.example.myapplication.Dashboard_Seller.pages.Sellerdashboardhome;
import com.example.myapplication.Dashboard_Seller.pages.Sellerdashboardorders;
import com.example.myapplication.LoadingUserActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.MenuItem;
import android.view.View;


public class SellerDashboard extends AppCompatActivity {
    private Fragment fhome = new Sellerdashboardhome();
    private Fragment faccount = new Sellerdashboardaccount();
    private Fragment forders = new Sellerdashboardorders();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment curr = fhome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        replaceFragment(fhome);

        BottomNavigationView bottomNavigation = findViewById(R.id.sellerdashboard_nav);


        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sellerdashboard_navmenu_home:
                        replaceFragment(fhome);
                        break;
                    case R.id.sellerdashboard_navmenu_account:
                        replaceFragment(faccount);
                        break;
                    case R.id.sellerdashboard_navmenu_orders:
                        replaceFragment(forders);
                        break;
                }
                return true;
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        if(fragment!=null){
            FragmentTransaction transactor = getSupportFragmentManager().beginTransaction();
            transactor.replace(R.id.sellerFragmentContainer, fragment);
            transactor.commit();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FirebaseUser curr = FirebaseAuth.getInstance().getCurrentUser();
        if(curr == null){
            Intent intent = new Intent(SellerDashboard.this, LoadingUserActivity.class);
            startActivity(intent);
        }

    }

    public void createStore(View view){
        Intent intent = new Intent(SellerDashboard.this, CreateNewStore.class);
        startActivity(intent);
    }

}