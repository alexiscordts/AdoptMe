package com.example.adoptme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adoptme.Accounts.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseLogin extends AppCompatActivity {

    private EditText mLoginEmail, mLoginPassword;
    private TextView mSignUp;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private DatabaseReference mUserReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get current app user
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(auth.getCurrentUser() != null){

            String uid = auth.getUid();
            mUserReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String type = dataSnapshot.child("type").getValue(String.class);
                    Toast.makeText(FirebaseLogin.this, type, Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };

            mUserReference.addListenerForSingleValueEvent(valueEventListener);

            startActivity(new Intent(FirebaseLogin.this, ShelterView.class));
            finish();
        }

        setContentView(R.layout.activity_firebase_login);

        mLoginEmail = findViewById(R.id.loginEmailInput);
        mLoginPassword = findViewById(R.id.loginPasswordInput);
        mSignUp = findViewById(R.id.loginSignUp);
    }

    public void onClickLoginButton(View v){
        auth = FirebaseAuth.getInstance();
        String email = mLoginEmail.getText().toString();
        final String password = mLoginPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Enter in email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Enter in password", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(FirebaseLogin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(FirebaseLogin.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }else{
//                    Intent intent = new Intent(FirebaseLogin.this, SwipePage.class);
                    Intent intent = new Intent(FirebaseLogin.this, SwipePage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void onSignUpButtonClick(View v){
        Intent intent = new Intent(getApplicationContext(), FirebaseCreateAccount.class);
        startActivity(intent);
    }
}
