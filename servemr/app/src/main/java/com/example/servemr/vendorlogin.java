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

public class vendorlogin extends AppCompatActivity {
EditText vuname,vpassword;
Button vlogin,vregist;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendorlogin);

        vuname= (EditText) findViewById(R.id.v1email);
        vpassword= (EditText) findViewById(R.id.v1password);

        vlogin= (Button) findViewById(R.id.v1login);
        vregist= (Button) findViewById(R.id.v1reg);
        auth=FirebaseAuth.getInstance();

        vlogin.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          String em = vuname.getText().toString().trim();
                                          String pass = vpassword.getText().toString().trim();

                                          if (TextUtils.isEmpty(em)) {

                                              vuname.setError("Missing Email field.");
                                              return;
                                          }

                                          if (TextUtils.isEmpty(pass)) {

                                              vpassword.setError("Missing password field.");
                                              return;
                                          }

                                          auth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(vendorlogin.this,new OnCompleteListener<AuthResult>() {
                                              @Override
                                              public void onComplete(@NonNull Task<AuthResult> task) {
                                                  if (task.isSuccessful()) {
                                                      Toast.makeText(vendorlogin.this,"Vendor Login Successful",Toast.LENGTH_SHORT).show();
                                                      startActivity(new Intent(getApplicationContext(), ven_dash.class));
                                                  } else {
                                                      Toast.makeText(vendorlogin.this, "Error !!! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                  }
                                              }
                                          });
                                      }

                                  });



        vregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1= new Intent(vendorlogin.this,vendorregistration.class);
                startActivity(int1);
                finish();
                return;
            }
        });
    }
}