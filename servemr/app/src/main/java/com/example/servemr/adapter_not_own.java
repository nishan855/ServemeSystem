package com.example.servemr;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class adapter_not_own extends RecyclerView.Adapter<adapter_not_own.holder> {


    Context ctx;
    List<model> tasklist;
    String user_id;
    String user_type,tk_id;
    private DatabaseReference taskref;
    private DatabaseReference bidref;
    FirebaseAuth fauth;


    public adapter_not_own(Context ctx,List<model> tasklist ){
        this.ctx=ctx;
        this.tasklist=tasklist;
        fauth=FirebaseAuth.getInstance();

        user_id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        taskref= FirebaseDatabase.getInstance().getReference().child("Tasks");
        bidref= FirebaseDatabase.getInstance().getReference().child("bids");
    }

    @NonNull
    @Override
    public adapter_not_own.holder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View vw= LayoutInflater.from(ctx).inflate(R.layout.users_row_layout,vg,false);
        return new adapter_not_own.holder(vw);
    }



    @Override
    public void onBindViewHolder(@NonNull adapter_not_own.holder myholder, int pos) {
        String us=tasklist.get(pos).getName();
        String pdate=tasklist.get(pos).getDate();
        String ptm=tasklist.get(pos).getTime();
        String des=tasklist.get(pos).getDescription();
        //String addr=tasklist.get(pos).getaddr();
        String categ=tasklist.get(pos).getCategory();
        String sdate=tasklist.get(pos).getOrder_date();
        String loc=tasklist.get(pos).getLocation();
        tk_id=tasklist.get(pos).getId();



        myholder.username.setText(us);
        myholder.pdate.setText("On "+pdate);
        myholder.ptime.setText("at "+ptm);
        myholder.desc.setText("Description:     "+des);
        myholder.addr.setText("Location:        "+loc);
        myholder.categ.setText("Category:       "+categ);
        myholder.sdate.setText("Service Date:   "+sdate);

        myholder.vbid.setText("Delete");

        myholder.vbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            String tskid = tk_id;
            final String TAG = "sorry exception";
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query delete_service = ref.child("Tasks").orderByChild("id").equalTo(tskid);

            delete_service.addListenerForSingleValueEvent(new ValueEventListener() {
                                                              @Override
                                                              public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                  for (DataSnapshot deleteSnapshot : snapshot.getChildren()) {
                                                                      deleteSnapshot.getRef().removeValue();
                                                                  }
                                                              }

                                                              @Override
                                                              public void onCancelled(@NonNull DatabaseError error) {
                                                                  Log.e(TAG, "onCancelled", error.toException());

                                                              }
                                                          });

                Intent intent= new Intent(ctx,customer_dashboard.class);
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasklist.size();
    }

    class holder extends RecyclerView.ViewHolder{
        TextView username,pdate,ptime,desc,addr,categ,sdate,stime;
        Button bid,vbid;


        public holder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.uusername);
            pdate=itemView.findViewById(R.id.udt);
            ptime=itemView.findViewById(R.id.utt);
            desc=itemView.findViewById(R.id.udesc);
            addr=itemView.findViewById(R.id.uadd);
            categ=itemView.findViewById(R.id.ucat);
            sdate=itemView.findViewById(R.id.udat);
            vbid=itemView.findViewById(R.id.ubid);

        }
    }


}
