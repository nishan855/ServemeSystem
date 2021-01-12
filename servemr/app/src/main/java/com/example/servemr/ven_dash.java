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

public class ven_dash extends AppCompatActivity {

    ActionBar act;
    FirebaseAuth fauth;
    BottomNavigationView nav;
    Fragment selected=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ven_dash);
        fauth = FirebaseAuth.getInstance();
        //act = getSupportActionBar();
        //act.setTitle("Vendor Dashboard");

        nav =findViewById(R.id.navigo);
        nav.setOnNavigationItemSelectedListener(navilist);
        getSupportFragmentManager().beginTransaction().replace(R.id.frme,new vendor_home()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navilist= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId()){
                case R.id.hm:
                    //act.setTitle("Vendor Home");
                    selected=new vendor_home();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frme,selected).commit();
                    break;


                case R.id.hst:
                    //act.setTitle("Vendor History");
                    selected=new vnewhistorylFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frme,selected).commit();
                    break;

                case R.id.vlt:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    break;
            }

            return true;
        }
    };

}