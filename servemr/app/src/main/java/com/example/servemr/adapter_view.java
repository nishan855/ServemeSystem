package com.example.servemr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class adapter_view extends RecyclerView.Adapter<adapter_view.mholder> {


    Context ctx;
    List<model_comm> bclist;
    String user_id;
    String user_type;
    private DatabaseReference acceptref;
    private DatabaseReference bidref;
    FirebaseAuth fauth;
    String tkid;
    Button accept;


    View mview;


    public adapter_view(Context ctx, List<model_comm> bclist) {
        this.ctx = ctx;
        this.bclist = bclist;
        fauth = FirebaseAuth.getInstance();
        user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @NonNull
    @Override
    public adapter_view.mholder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View vw = LayoutInflater.from(ctx).inflate(R.layout.fragment_c_search, vg, false);
        return new adapter_view.mholder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull final adapter_view.mholder myholder, int pos) {

        final String bid = bclist.get(pos).getBids();
        final String dat = bclist.get(pos).getDt();
        final String nam =  bclist.get(pos).getName();
        final String tm = bclist.get(pos).getTm();
        tkid = bclist.get(pos).getTskid();
        final String v_id = bclist.get(pos).getUid();
        bidref = FirebaseDatabase.getInstance().getReference("Bids").child(tkid);






        final String ttm = System.currentTimeMillis() + "";



        myholder.udt.setText(dat);
        myholder.utt.setText(tm);
        myholder.username.setText(nam);
        myholder.vbids.setText("$ " + bid);


        myholder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptref = FirebaseDatabase.getInstance().getReference().child("Accepted_task").child(tkid);
                HashMap<String, Object> accepted = new HashMap<>();
                String bid_id = String.valueOf(System.currentTimeMillis());
                accepted.put("vname", nam);
                accepted.put("cost", bid);
                accepted.put("time", dat +" "+tm);
                accepted.put("taskid", tkid);
                accepted.put("c_id", fauth.getUid());
                accepted.put("ven_id", v_id);
                acceptref.updateChildren(accepted);

                Toast.makeText(ctx, "Proceed to Payment", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(ctx,pay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("cost",bid);
                ctx.startActivity(intent);


                //Intent intent= new Intent(ctx,pay.class);
                //intent.putExtra("cost",bid);
                //ctx.startActivity(intent);



            }
        });
    }


    public int getItemCount() {
        return bclist.size();
    }

    class mholder extends RecyclerView.ViewHolder {
        TextView username, vbids;
        TextView udt, utt;
        Button select;


        public mholder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tuserbd);
            vbids = itemView.findViewById(R.id.tval);
            udt = itemView.findViewById(R.id.tudt);
            utt = itemView.findViewById(R.id.tutt);

            select = itemView.findViewById(R.id.pay);



        }
    }
}





