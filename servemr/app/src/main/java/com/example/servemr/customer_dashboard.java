package com.example.servemr;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class customer_dashboard extends AppCompatActivity {

    ActionBar act;
    FirebaseAuth fauth;
    BottomNavigationView navg;
    Fragment selected=null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);
        fauth = FirebaseAuth.getInstance();
        //act = getSupportActionBar();
        //act.setTitle("Vendor Dashboard");

        navg =findViewById(R.id.navy);
        navg.setOnNavigationItemSelectedListener(navylist);
        getSupportFragmentManager().beginTransaction().replace(R.id.search_edit_frame,new cHomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navylist= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId()){
                case R.id.chm:
                    //act.setTitle("Vendor Home");
                    selected=new cHomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.search_edit_frame,selected).commit();
                    break;
                case R.id.csvr:
                    //act.setTitle("Vendor Services");
                    selected=new cServicesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.search_edit_frame,selected).commit();
                    break;

                case R.id.chst:
                    selected=new cnewhistorylFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.search_edit_frame,selected).commit();

                    break;
                case R.id.clt:
                    //act.setTitle("Vendor Profile");
                    //selected=new cPaymentFragment();
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    break;

            }

            return true;
        }
    };

}