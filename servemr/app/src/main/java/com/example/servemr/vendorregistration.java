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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class vendorregistration extends AppCompatActivity {

    EditText _name, email, phone, password, address, state, zip, category, rate;
    Button vreg;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    DatabaseReference vndr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendorregistration);

        _name = (EditText) findViewById(R.id.vname);
        email = (EditText) findViewById(R.id.vemail);
        phone = (EditText) findViewById(R.id.vphone);
        password = (EditText) findViewById(R.id.vpass);
        address = (EditText) findViewById(R.id.vadd);
        state = (EditText) findViewById(R.id.vstate);
        zip = (EditText) findViewById(R.id.vzip);
        category = (EditText) findViewById(R.id.category);
        rate = (EditText) findViewById(R.id.samplerate);

        vreg = (Button) findViewById(R.id.butvreg);

        fstore=FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ven_dash.class));
            finish();
        }
        vreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final  String em= email.getText().toString().trim();
                final String pass= password.getText().toString().trim();
                final String nam= _name.getText().toString().trim();
                final String ph= phone.getText().toString().trim();
                final String add= address.getText().toString().trim();
                final String st= state.getText().toString().trim();
               final  String zipp= zip.getText().toString().trim();
                final String rat= rate.getText().toString().trim();
               final String cat= category.getText().toString().trim();


                if(TextUtils.isEmpty(em)){

                    email.setError("Missing Email field.");
                    return;
                }

                if(TextUtils.isEmpty(pass)){

                    password.setError("Missing password field.");
                    return;
                }
                if(TextUtils.isEmpty(nam)){

                    _name.setError("Missing name field.");
                    return;
                }
                if(TextUtils.isEmpty(ph)){

                    phone.setError("Missing phone field.");
                    return;
                }
                if(TextUtils.isEmpty(add)){

                    address.setError("Missing address field.");
                    return;
                }
                if(TextUtils.isEmpty(st)){

                    state.setError("Missing state field.");
                    return;
                }
                if(TextUtils.isEmpty(cat)){

                    category.setError("Missing category field.");
                    return;
                }


                if(TextUtils.isEmpty(zipp)){

                    zip.setError("Missing zip field.");
                    return;
                }

                if(pass.length()<6){
                    password.setError("Password must be at least 6 character long.");
                    return;
                }

                auth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(vendorregistration.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String Userid;
                        if(task.isSuccessful()){
                            Userid=auth.getCurrentUser().getUid();
                            vndr = FirebaseDatabase.getInstance().getReference().child("Vendors").child(Userid);
                            Map<String,Object> vend=new HashMap<>();
                            vend.put("Fname",nam);
                            vend.put("Email",em);
                            vend.put("password",pass);
                            vend.put("Phone",ph);
                            vend.put("Address",add);
                            vend.put("State",st);
                            vend.put("zip",zipp);
                            vend.put("rate",rat);
                            vend.put("category",cat);
                            vend.put("id",Userid);
                            vend.put("type","vendor");
                            vndr.updateChildren(vend).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(vendorregistration.this,"Service Provider Registration Successful",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        String message =  task.getException().getMessage();
                                        Toast.makeText(vendorregistration.this, "Error " + message, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), ven_dash.class));
                        }
                        else{
                            Toast.makeText(vendorregistration.this,"Error !!! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            }
        });
    }
}