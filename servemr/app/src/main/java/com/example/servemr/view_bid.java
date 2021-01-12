package com.example.servemr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class view_bid extends AppCompatActivity {


    List<model_comm> comlist;
    adapter_view adpw;
    LinearLayout llt;
    EditText cmt;
    ActionBar act;
    String tkid, dt, tm;
    FirebaseAuth fauth;
    public RecyclerView rclb;
    TextView tv;
    String User,mytask;
    String category, order_date, order_time, description, name, time, date, location, tid,cmt_name;





    TextView username,pdate,ptime,desc,addr,categ,sdate,stime;
    Button cancel;


    ProgressBar pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bid);

        act = getSupportActionBar();
        act.setDisplayShowHomeEnabled(true);
        act.setDisplayHomeAsUpEnabled(true);
        cancel=findViewById(R.id.cantv);
        rclb=findViewById(R.id.curecl);


        Intent int1 = getIntent();
        tkid = int1.getStringExtra("ctxid");
        act.setTitle("View Bids");




        view_all();



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), customer_dashboard.class));
            }

        });
    }


    private void view_all() {
        LinearLayoutManager lm=new LinearLayoutManager(getApplicationContext());
        rclb.setLayoutManager(lm);
        comlist=new ArrayList<>();
        comlist=new ArrayList<>();
       DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Bids");
        Query qr= FirebaseDatabase.getInstance().getReference("Bids").child(tkid).orderByChild("tskid").equalTo(tkid);
        qr.addValueEventListener(new ValueEventListener() {

           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               comlist.clear();
              for(DataSnapshot ds: snapshot.getChildren()){
                   model_comm md=ds.getValue(model_comm.class);

                    comlist.add(md);

                    adpw=new adapter_view(getApplicationContext(),comlist);
                   rclb.setAdapter(adpw);
               } }
              @Override public void onCancelled(@NonNull DatabaseError derror) {

               Toast.makeText(getApplicationContext(), ""+derror.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });


    }


}




