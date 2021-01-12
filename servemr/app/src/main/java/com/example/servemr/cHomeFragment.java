package com.example.servemr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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


public class cHomeFragment extends Fragment {

    FirebaseAuth fauth;

    RecyclerView rcl;
    List<model> tasklist;
    adapter_own adpsts;



    public cHomeFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_c_home, container, false);

        rcl = vw.findViewById(R.id.reclr);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setStackFromEnd(true);
        lm.setReverseLayout(true);
        rcl.setLayoutManager(lm);

        tasklist = new ArrayList<>();
        viewall();
        return vw;

    }

    private void viewall() {
        fauth = FirebaseAuth.getInstance();
        final String user=fauth.getUid();

        DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Tasks");
        //dref.addListenerForSingleValueEvent(valel);
        Query qr= FirebaseDatabase.getInstance().getReference("Tasks").orderByChild("uid").equalTo(user);
        qr.addListenerForSingleValueEvent(valel);

    }

    ValueEventListener valel= new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            tasklist.clear();
            if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()) {

                        model md = ds.getValue(model.class);

                        //if(md.getUid()==user) {
                        tasklist.add(md);

                        adpsts = new adapter_own(getActivity(), tasklist);
                        rcl.setAdapter(adpsts);
                        //}
                    }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError derror) {

            Toast.makeText(getActivity(), ""+derror.getMessage(),Toast.LENGTH_SHORT).show();
        }

    };

}