package com.example.servemr;

import android.content.Context;
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



public class adapterVendor extends RecyclerView.Adapter<adapterVendor.mholder> {

    Context ctx;
    List<modelvendor> vendorlist;
    String user_id;
    String user_type;
    private DatabaseReference acceptref;
    private DatabaseReference bidref;
    FirebaseAuth fauth;
    String tskid;


    View mview;


    public adapterVendor(Context ctx,List<modelvendor> comlist ){
        this.ctx=ctx;
        this.vendorlist=vendorlist;
        fauth=FirebaseAuth.getInstance();
        user_id= FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @NonNull
    @Override
    public adapterVendor.mholder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View vw= LayoutInflater.from(ctx).inflate(R.layout.user_row,vg,false);
        return new adapterVendor.mholder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterVendor.mholder myholder, int pos) {
        final String bid=vendorlist.get(pos).getAddress();
        final String dat=vendorlist.get(pos).getEmail();
        final String nam=vendorlist.get(pos).getFname();
        final String tm=vendorlist.get(pos).getId();
        tskid=vendorlist.get(pos).getPhone();
        final String uid=vendorlist.get(pos).getState();
        bidref= FirebaseDatabase.getInstance().getReference("Bids").child(tskid);





        final String ttm=System.currentTimeMillis()+"";


        myholder.username.setText(nam);


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
        return vendorlist.size();
    }

    class mholder extends RecyclerView.ViewHolder{
        TextView username,vbids;
        TextView udt,utt;
        // Button select;


        public mholder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.usr);


            //select=itemView.findViewById(R.id.select);


        }
    }
}
