package com.example.servemr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class adapter_comments extends RecyclerView.Adapter<adapter_comments.mholder> {



    Context ctx;
    List<model_comm> comlist;
    String user_id;
    String user_type;
    private DatabaseReference acceptref;
    private DatabaseReference bidref;
    FirebaseAuth fauth;
    String tskid;


    View mview;


    public adapter_comments(Context ctx,List<model_comm> comlist ){
        this.ctx=ctx;
        this.comlist=comlist;
        fauth=FirebaseAuth.getInstance();
        user_id= FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @NonNull
    @Override
    public adapter_comments.mholder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View vw= LayoutInflater.from(ctx).inflate(R.layout.row_select_layout,vg,false);
        return new adapter_comments.mholder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_comments.mholder myholder, int pos) {
        final String bid=comlist.get(pos).getBids();
        final String dat=comlist.get(pos).getDt();
        final String nam=comlist.get(pos).getName();
        final String tm=comlist.get(pos).getTm();
        tskid=comlist.get(pos).getTskid();
        final String uid=comlist.get(pos).getUid();
        bidref= FirebaseDatabase.getInstance().getReference("Bids").child(tskid);


        Intent intent= new Intent(ctx,bid.class);
        intent.putExtra("bd",tskid);



        final String ttm=System.currentTimeMillis()+"";


        myholder.udt.setText(dat);
        myholder.utt.setText(tm);
        myholder.username.setText(nam);
        myholder.vbids.setText("$ "+bid);


       // myholder.select.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {
             //   acceptref= FirebaseDatabase.getInstance().getReference().child("Accepted_task").child(ttm);
               // HashMap<String, Object> accepted = new HashMap<>();
                //tring bid_id = String.valueOf(System.currentTimeMillis());
                //accepted.put("vname", nam);
                //accepted.put("cost",bid);
                //accepted.put("time", tm);
                //accepted.put("taskid",tskid);
                //accepted.put("c_id", fauth.getUid());
                //accepted.put("ven_id",uid);


            }



    public int getItemCount() {
        return comlist.size();
    }

    class mholder extends RecyclerView.ViewHolder{
        TextView username,vbids;
        TextView udt,utt;
       // Button select;


        public mholder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.userbd);
            vbids=itemView.findViewById(R.id.val);
            udt=itemView.findViewById(R.id.udt);
            utt=itemView.findViewById(R.id.utt);

            //select=itemView.findViewById(R.id.select);


        }
    }
}


