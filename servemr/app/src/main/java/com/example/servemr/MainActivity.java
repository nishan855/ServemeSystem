package com.example.servemr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button customer;
    private Button vendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customer= (Button) findViewById(R.id.cust);
        vendor= (Button) findViewById(R.id.vend);

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1= new Intent(MainActivity.this,custlinActivity.class);
                startActivity(int1);


            }
        });

        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1= new Intent(MainActivity.this,vendorlogin.class);
                startActivity(int1);

            }
        });
    }
}