package com.example.servemr;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class vprofile_fragment extends Fragment {

    EditText name;
    EditText email;
    EditText phone;
    EditText password;
    EditText address;
    EditText state;
    EditText zip;
    Button creg;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String user_id;

    String _NAME,_PHONE,_PASSWORD,_ADDRESS,_STATE,_ZIP;

    DatabaseReference reference;


    public vprofile_fragment() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw= inflater.inflate(R.layout.fragment_vprofile_fragment, container, false);
        reference = FirebaseDatabase.getInstance().getReference().child("Vendors");
         user_id = fauth.getCurrentUser().getUid();


        name = (EditText) vw.findViewById(R.id.nm);
        phone = (EditText)vw.findViewById(R.id.phn);
        password = (EditText)vw.findViewById(R.id.ps);
        address = (EditText)vw.findViewById(R.id.adrr);
        state = (EditText)vw.findViewById(R.id.stt);
        zip = (EditText)vw.findViewById(R.id.zp);
        fauth = FirebaseAuth.getInstance();
        creg= (Button) vw.findViewById(R.id.but);

        showAllUserData();
        return vw;
    }



    private void showAllUserData(){

        Intent intent= getActivity().getIntent();
        _NAME = intent.getStringExtra("name");
        _PHONE = intent.getStringExtra("phone");
        _PASSWORD = intent.getStringExtra("password");
        _ADDRESS = intent.getStringExtra("address");
        _STATE = intent.getStringExtra("state");
        _ZIP = intent.getStringExtra("zip");

        name.setText(_NAME);
        phone.setText(_PHONE);
        password.setText(_PASSWORD);
        address.setText(_ADDRESS);
        state.setText(_STATE);
        zip.setText(_ZIP);

    }


    public void update(View view){
        if(isNameChanged() || isPasswordChanged()|| isPhoneChanged() || isAddressChanged() || isStateChanged() || isZipChanged()){
            Toast.makeText(getActivity(), "Successfully updated.", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(getActivity(), "Information updated is same.", Toast.LENGTH_LONG).show();
    }

    private boolean isPasswordChanged(){

        if(!_PASSWORD.equals(password.getText().toString())){
            reference.child(user_id).child("password").setValue(password.getText().toString());
            _PASSWORD = password.getText().toString();
            return true;
        }
        else{
            return false;
        }

    }
    private boolean isNameChanged(){
        if(!_NAME.equals(name.getText().toString())){
            reference.child(user_id).child("Fname").setValue(name.getText().toString());
            _NAME = name.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isPhoneChanged(){
        if(!_PHONE.equals(phone.getText().toString())){
            reference.child(user_id).child("Phone").setValue(phone.getText().toString());
            _PHONE = phone.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isAddressChanged(){
        if(!_ADDRESS.equals(address.getText().toString())){
            reference.child(user_id).child("password").setValue(address.getText().toString());
            _ADDRESS = address.getText().toString();
            return true;
        }
        else{
            return false;
        }

    }

    private boolean isStateChanged(){
        if(!_STATE.equals(state.getText().toString())){
            reference.child(user_id).child("password").setValue(state.getText().toString());
            _STATE = state.getText().toString();
            return true;
        }
        else{
            return false;
        }

    }

    private boolean isZipChanged(){
        if(!_ZIP.equals(zip.getText().toString())){
            reference.child(user_id).child("zip").setValue(zip.getText().toString());
            _ZIP= zip.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }
}