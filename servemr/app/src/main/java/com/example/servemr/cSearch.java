package com.example.servemr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cSearch extends AppCompatActivity {
    List<modelvendor> comlist;
    adapterVendor adpv;
    Button search;
    LinearLayout llt;
    EditText searchtxt;
    ActionBar act;
    String txt;
    FirebaseAuth fauth;
    public RecyclerView rclvw;
    String User,mytask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        act = getSupportActionBar();
        act.setTitle("Search Vendors");
        act.setDisplayShowHomeEnabled(true);
        act.setDisplayHomeAsUpEnabled(true);


        //mytask=int1.getStringExtra("bd");

        search = findViewById(R.id.but_srch);
        searchtxt = findViewById(R.id.val_srch);

        llt = findViewById(R.id.loutt);
        rclvw = findViewById(R.id.reclrr);

        fauth = FirebaseAuth.getInstance();
        User = fauth.getUid();





        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                txt = searchtxt.getText().toString().trim().toLowerCase();
               viewall();


            }
        });

    }

    private void viewall() {

        comlist=new ArrayList<>();
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Vendors");
        Query qr= FirebaseDatabase.getInstance().getReference("Vendors").orderByChild("Fname").equalTo(txt);
        qr.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comlist.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    modelvendor md=ds.getValue(modelvendor.class);

                    comlist.add(md);

                    adpv=new adapterVendor(getApplicationContext(),comlist);
                    rclvw.setAdapter(adpv);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError derror) {

                Toast.makeText(getApplicationContext(), ""+derror.getMessage(),Toast.LENGTH_SHORT).show();
            }

        });


    }




}

