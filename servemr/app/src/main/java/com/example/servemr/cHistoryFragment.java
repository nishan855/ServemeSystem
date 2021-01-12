package com.example.servemr;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class cHistoryFragment extends AppCompatActivity {


    List<model_history> comlist;
    adapter_history adph;
    LinearLayout llt;
    ActionBar act;
    String tkid, dt, tm;
    FirebaseAuth fauth;
    public RecyclerView rclh;
    String category, order_date, order_time, description, name, time, date, location, tid,cmt_name;








    ProgressBar pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_c_home);

        act = getSupportActionBar();
        act.setDisplayShowHomeEnabled(true);
        act.setDisplayHomeAsUpEnabled(true);

        rclh = findViewById(R.id.reclr);


        act.setTitle("View Schedules");


        view_all();


    }


    private void view_all() {
        String user=FirebaseAuth.getInstance().getUid();
        LinearLayoutManager lm=new LinearLayoutManager(getApplicationContext());
        rclh.setLayoutManager(lm);
        comlist=new ArrayList<>();
        comlist=new ArrayList<>();
       // DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Bids");
        Query qr= FirebaseDatabase.getInstance().getReference("Accepted_task").orderByChild("c_id").equalTo(user);
        qr.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comlist.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    model_history md=ds.getValue(model_history.class);

                    comlist.add(md);

                    adph=new adapter_history(getApplicationContext(),comlist);
                    rclh.setAdapter(adph);
                } }
            @Override public void onCancelled(@NonNull DatabaseError derror) {

                Toast.makeText(getApplicationContext(), ""+derror.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });


    }


}




