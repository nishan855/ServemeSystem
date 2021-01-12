package com.example.servemr;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class update extends AppCompatActivity {
    EditText cat, date, time, desc;

    Button place;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String dt,tm,rand;
    DatabaseReference postref, custref;



    public void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);


        cat = (EditText) findViewById(R.id.pcat);
        date = (EditText) findViewById(R.id.pdate);
        time = (EditText) findViewById(R.id.ptime);
        place = (Button) findViewById(R.id.pplace);
        desc = (EditText) findViewById(R.id.desct);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        custref = FirebaseDatabase.getInstance().getReference().child("Customers");
        postref = FirebaseDatabase.getInstance().getReference().child("Tasks");

        Calendar dat = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        dt = currentDate.format(dat.getTime());

        Calendar tim = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        tm = currentTime.format(tim.getTime());


        rand = dt+tm;

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String categ = cat.getText().toString().trim();
                final String pdt = date.getText().toString().trim();
                final String ptm = time.getText().toString().trim();
                final String dcr = desc.getText().toString().trim();


                if (TextUtils.isEmpty(categ)) {

                    cat.setError("Missing  field.");
                    return;
                }

                if (TextUtils.isEmpty(pdt)) {

                    date.setError("Missing field.");
                    return;
                }
                if (TextUtils.isEmpty(ptm)) {

                    time.setError("Missing field.");
                    return;
                }
                final String user_id = fauth.getCurrentUser().getUid();


                final Map<String, Object> order = new HashMap<>();

                custref=custref.child(user_id);
                custref.addValueEventListener(new ValueEventListener() {

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String fname = dataSnapshot.child("Fname").getValue().toString();
                            String add=dataSnapshot.child("Address").getValue().toString();
                            String st=dataSnapshot.child("State").getValue().toString();
                            String zp=dataSnapshot.child("zip").getValue().toString();

                            String location=add+", "+st+", "+zp;
                            order.put("location",location);
                            order.put("category", categ);
                            order.put("order_date", pdt);
                            order.put("description", dcr);
                            order.put("order_time", ptm);
                            order.put("name", fname);
                            order.put("date", dt);
                            order.put("time", tm);
                            String  id=user_id+rand;
                            order.put("id",id);
                            order.put("uid",user_id);


                            postref.child(id).updateChildren(order);
                            Toast.makeText(getApplicationContext(),"Service call Placed Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),customer_dashboard.class));





                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

        });

    }
}