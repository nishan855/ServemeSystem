package com.example.servemr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class adapterposts extends RecyclerView.Adapter<adapterposts.Myholder> {

    Context ctx;
    List<model> tasklist;
    String user_id;
    private DatabaseReference taskref;
    private DatabaseReference bidref;
    FirebaseAuth fauth;

    public adapterposts(Context ctx,List<model> tasklist ){
        this.ctx=ctx;
        this.tasklist=tasklist;
        fauth=FirebaseAuth.getInstance();
        user_id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        taskref= FirebaseDatabase.getInstance().getReference().child("Tasks");
        bidref= FirebaseDatabase.getInstance().getReference().child("bids");
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View vw= LayoutInflater.from(ctx).inflate(R.layout.all_layout,vg,false);
        return new Myholder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder myholder, int pos) {
        String us=tasklist.get(pos).getName();
        String pdate=tasklist.get(pos).getDate();
        String ptm=tasklist.get(pos).getTime();
        String des=tasklist.get(pos).getDescription();
        //String addr=tasklist.get(pos).getaddr();
        String categ=tasklist.get(pos).getCategory();
        String sdate=tasklist.get(pos).getOrder_date();
        String loc=tasklist.get(pos).getLocation();
        final String tsk=tasklist.get(pos).getId();

        myholder.username.setText(us);
        myholder.pdate.setText("On "+pdate);
        myholder.ptime.setText("at "+ptm);
        myholder.desc.setText("Description:     "+des);
        myholder.addr.setText("Location:        "+loc);
        myholder.categ.setText("Category:       "+categ);
        myholder.sdate.setText("Service Date:   "+sdate);

        myholder.bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ctx,bid.class);
                intent.putExtra("tsk",tsk);
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasklist.size();
    }

    class Myholder extends RecyclerView.ViewHolder{
        TextView username,pdate,ptime,desc,addr,categ,sdate,stime;
        Button bid;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            pdate=itemView.findViewById(R.id.d);
            ptime=itemView.findViewById(R.id.t);
            desc=itemView.findViewById(R.id.desc);
            addr=itemView.findViewById(R.id.add);
            categ=itemView.findViewById(R.id.cat);
            sdate=itemView.findViewById(R.id.dat);
            bid=itemView.findViewById(R.id.bid);

        }
    }


}
