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

public class custlinActivity extends AppCompatActivity {
private EditText cuname,cpassword;
private Button clogin,cregist;
    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custlin);
        cuname= (EditText) findViewById(R.id.c1name);
        cpassword= (EditText) findViewById(R.id.cpass1);

        clogin= (Button) findViewById(R.id.c1ln);
        cregist= (Button) findViewById(R.id.c1reg);
        fauth= FirebaseAuth.getInstance();

        clogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        String em= cuname.getText().toString().trim();
                        String pass= cpassword.getText().toString().trim();

                        if(TextUtils.isEmpty(em)){

                            cuname.setError("Missing Email field.");
                            return;
                        }

                        if(TextUtils.isEmpty(pass)) {

                            cpassword.setError("Missing password field.");
                            return;
                        }

                        fauth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(custlinActivity.this,new OnCompleteListener<AuthResult>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                                if(task.isSuccessful()) {
                                                                                                    Toast.makeText(custlinActivity.this,"Customer Login Successful",Toast.LENGTH_SHORT).show();
                                                                                                    startActivity(new Intent(getApplicationContext(),customer_dashboard.class));
                                                                                                }

                                                                                                else{
                                                                                                    Toast.makeText(custlinActivity.this,"Error !!! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                            }
                                                                                        });
            }
        });

        cregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1= new Intent(custlinActivity.this,customerregistration.class);
                startActivity(int1);
                finish();
                return;
            }
        });
    }
}
