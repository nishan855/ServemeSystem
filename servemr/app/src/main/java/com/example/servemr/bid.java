package com.example.servemr;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class bid extends AppCompatActivity {

    List<model_comm> comlist;
    adapter_comments adpc;
    Button post;
    LinearLayout llt;
    EditText cmt;
    ActionBar act;
    String tskid, dt, tm;
    FirebaseAuth fauth;
    public RecyclerView rclb;
    TextView tv;
    String User,mytask;
    String category, order_date, order_time, description, name, time, date, location, tid,cmt_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);

        act = getSupportActionBar();
        act.setTitle("Post Bid");
       act.setDisplayShowHomeEnabled(true);
        act.setDisplayHomeAsUpEnabled(true);


        Intent int1 = getIntent();
        tskid = int1.getStringExtra("tsk");

        //mytask=int1.getStringExtra("bd");

        post = findViewById(R.id.but_post);
        cmt = findViewById(R.id.val_bid);

        llt = findViewById(R.id.loutt);
        rclb=findViewById(R.id.reclvw);
        fauth = FirebaseAuth.getInstance();
        User = fauth.getUid();

        //tv.setText(tskid);
        Calendar dat = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        dt = currentDate.format(dat.getTime());

        Calendar tim = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        tm = currentTime.format(tim.getTime());
        viewall();





        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String cbid = cmt.getText().toString().trim();

                if (TextUtils.isEmpty(cbid)) {
                    Toast.makeText(getApplicationContext(), "Missing the bid", Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Vendors").child(User);
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            cmt_name=snapshot.child("Fname").getValue().toString();
                            DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Bids").child(tskid).child(User);
                            HashMap<String, Object> bidmap = new HashMap<>();
                            String bid_id = String.valueOf(System.currentTimeMillis());
                            bidmap.put("bids", cbid);
                            bidmap.put("uid",User);
                            bidmap.put("dt", dt);
                            bidmap.put("tm", tm);
                            bidmap.put("tskid", tskid);
                            bidmap.put("name",cmt_name);

                            dref.updateChildren(bidmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Bid Posted Successfully", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), ven_dash.class));

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_LONG).show();

                                    }

                                }
                            });

                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });

            }


        });

    }

    private void viewall() {
        LinearLayoutManager lm=new LinearLayoutManager(getApplicationContext());
        rclb.setLayoutManager(lm);
        comlist=new ArrayList<>();
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Bids");
        Query qr= FirebaseDatabase.getInstance().getReference("Bids").child(tskid).orderByChild("tskid").equalTo(tskid);
        qr.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comlist.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    model_comm md=ds.getValue(model_comm.class);

                    comlist.add(md);

                    adpc=new adapter_comments(getApplicationContext(),comlist);
                    rclb.setAdapter(adpc);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError derror) {

                Toast.makeText(getApplicationContext(), ""+derror.getMessage(),Toast.LENGTH_SHORT).show();
            }

        });


    }




}

