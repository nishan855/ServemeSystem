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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class vendor_home extends Fragment {

    FirebaseAuth fauth;

    RecyclerView rcl;
    List<model> tasklist;
    adapterposts adpsts;

    public vendor_home() {

    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup cnt, Bundle saveIns) {
        View vw = inf.inflate(R.layout.vendor_home, cnt, false);
        fauth = FirebaseAuth.getInstance();

        rcl = vw.findViewById(R.id.recl);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setStackFromEnd(true);
        lm.setReverseLayout(true);
        rcl.setLayoutManager(lm);

        tasklist = new ArrayList<>();
        viewall();
        return vw;
    }

    private void viewall() {

        DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child("Tasks");
        dref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tasklist.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    model md=ds.getValue(model.class);

                    tasklist.add(md);

                    adpsts=new adapterposts(getActivity(),tasklist);
                    rcl.setAdapter(adpsts);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError derror) {

                Toast.makeText(getActivity(), ""+derror.getMessage(),Toast.LENGTH_SHORT).show();
            }

        });


    }

}