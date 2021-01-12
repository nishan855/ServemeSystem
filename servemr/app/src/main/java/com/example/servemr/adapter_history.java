package com.example.servemr;

import android.content.Context;
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

public class adapter_history extends RecyclerView.Adapter<adapter_history.holder> {


    Context ctx;
    List<model_history> tasklist;
    String user_id;
    String user_type,tk_id;
    private DatabaseReference taskref;
    private DatabaseReference bidref;
    FirebaseAuth fauth;


    public adapter_history(Context ctx, List<model_history> tasklist ){
        this.ctx=ctx;
        this.tasklist=tasklist;
        fauth=FirebaseAuth.getInstance();

        user_id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        taskref= FirebaseDatabase.getInstance().getReference().child("Tasks");
        bidref= FirebaseDatabase.getInstance().getReference().child("bids");
    }

    @NonNull
    @Override
    public adapter_history.holder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View vw= LayoutInflater.from(ctx).inflate(R.layout.cust_hist_row,vg,false);
        return new adapter_history.holder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_history.holder myholder, int pos) {
        String cost=tasklist.get(pos).getCost();
        String cid=tasklist.get(pos).getCid();
        String taskid=tasklist.get(pos).getTaskid();
        String tm=tasklist.get(pos).getTime();
        //String addr=tasklist.get(pos).getaddr();
        String ven=tasklist.get(pos).getVen_id();
        String vname=tasklist.get(pos).getVname();







        myholder.username.setText("Vendor name       "+vname);
        myholder.pdate.setText("Service Date:   "+tm);
        myholder.cost.setText("Cost       "+cost);





    }

    @Override
    public int getItemCount() {
        return tasklist.size();
    }

    class holder extends RecyclerView.ViewHolder{
        TextView username,pdate,cost;
        Button bid,vbid;


        public holder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.uusername);
            pdate=itemView.findViewById(R.id.udt);
            cost=itemView.findViewById(R.id.utt);


        }
    }


}
