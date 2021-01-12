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

public class customerregistration extends AppCompatActivity {
    EditText name,email,phone,password,address,state,zip;
    Button creg;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    DatabaseReference custmref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerregistration);

        name= (EditText) findViewById(R.id.cname);
        email= (EditText) findViewById(R.id.cemail);
        phone= (EditText) findViewById(R.id.cphone);
        password= (EditText) findViewById(R.id.cpassword);
        address= (EditText) findViewById(R.id.caddress);
        state=(EditText) findViewById(R.id.cstate);
        zip= (EditText) findViewById(R.id.czip);

        creg= (Button) findViewById(R.id.creg);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        if(fauth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),customer_dashboard.class));
            finish();
        }

        creg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String em= email.getText().toString().trim();
                final String pass= password.getText().toString().trim();
               final String nam= name.getText().toString().trim();
                final String ph= phone.getText().toString().trim();
               final String add= address.getText().toString().trim();
                final String st= state.getText().toString().trim();
                final String zipp= zip.getText().toString().trim();

                if(TextUtils.isEmpty(em)){

                    email.setError("Missing Email field.");
                    return;
                }

                if(TextUtils.isEmpty(pass)){

                    password.setError("Missing password field.");
                    return;
                }
                if(TextUtils.isEmpty(nam)){

                    name.setError("Missing name field.");
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
                if(TextUtils.isEmpty(zipp)){

                    zip.setError("Missing zip field.");
                    return;
                }

                if(pass.length()<6){
                    password.setError("Password must be at least 6 character long.");
                    return;
                }

                fauth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(customerregistration.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            String user_id = fauth.getCurrentUser().getUid();
                            custmref = FirebaseDatabase.getInstance().getReference().child("Customers").child(user_id);
                            Map<String,Object> cust=new HashMap<>();
                            cust.put("Fname",nam);
                            cust.put("Email",em);
                            cust.put("password",pass);
                            cust.put("Phone",ph);
                            cust.put("Address",add);
                            cust.put("State",st);
                            cust.put("zip",zipp);
                            cust.put("id",user_id);
                            cust.put("type","customer");
                            custmref.updateChildren(cust);
                            Toast.makeText(customerregistration.this,"Customer Registration Successful",Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(),customer_dashboard.class));
                        }
                        else{
                            Toast.makeText(customerregistration.this,"Error !!! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            }
        });

    }
}