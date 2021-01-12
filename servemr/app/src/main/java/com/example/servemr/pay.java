package com.example.servemr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class pay extends AppCompatActivity {

    Button pay;
    TextView cost;
    ActionBar act;
    String cos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);





        act = getSupportActionBar();
        act.setDisplayShowHomeEnabled(true);
        act.setDisplayHomeAsUpEnabled(true);
        pay=findViewById(R.id.pay);
        cost=findViewById(R.id.bval);

        Intent int1 = getIntent();
        cos = int1.getStringExtra("cost");

        act.setTitle("Payment");
        cost.setText(cos);

pay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        startActivity(new Intent(getApplicationContext(), customer_dashboard.class));
        Toast.makeText(getApplicationContext(),"Your Call is Confirmed",Toast.LENGTH_SHORT).show();

    }
});


    }
}