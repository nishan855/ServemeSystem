package com.example.servemr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class cServicesFragment extends Fragment {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Button place,date,cancel,bids;
    TextView tv;


    public cServicesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw= inflater.inflate(R.layout.fragment_c_services, container, false);
        place = vw.findViewById(R.id.cplace);

        cancel = vw.findViewById(R.id.ccanc);
        tv= vw.findViewById(R.id.cservices);

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               place pl= new place();
                getFragmentManager().beginTransaction().replace(R.id.search_edit_frame,pl).commit();
            }


        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ccancelcallFragment pl= new ccancelcallFragment();
                getFragmentManager().beginTransaction().replace(R.id.search_edit_frame,pl).commit();

            }
        });




        return vw;
    }
}