package com.example.adoptme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adoptme.Accounts.Adopter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseCreateAccount extends AppCompatActivity {

    private EditText mName, mEmail, mPassword, mAcctType;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_create_account);

        mName = findViewById(R.id.createAccountInputName);
        mEmail = findViewById(R.id.createAccountInputEmail);
        mPassword = findViewById(R.id.createAccountInputPassword);
        mAcctType = findViewById(R.id.createAccountInputAcctType);

        auth = FirebaseAuth.getInstance();
    }

    public void onClickCreateAccount(View v){
        final String name = mName.getText().toString();
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        final String acctType = mAcctType.getText().toString().trim();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(acctType)){
            Toast.makeText(getApplicationContext(), "Missing information!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() < 6){
            Toast.makeText(getApplicationContext(), "Password is too short!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(FirebaseCreateAccount.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(FirebaseCreateAccount.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                if(!task.isSuccessful()){
                    Toast.makeText(FirebaseCreateAccount.this, "Authentication Failed: " + task.getException(), Toast.LENGTH_SHORT);
                }else{
                    String uid = auth.getUid();
                    Toast.makeText(FirebaseCreateAccount.this, "User ID: " + uid, Toast.LENGTH_SHORT).show();
                    if(acctType == "adopter"){
                        Adopter adopter = new Adopter(uid, name, acctType);
                        
                    }
                }
            }
        });
    }
}