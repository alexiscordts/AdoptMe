package com.example.adoptme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseLogin extends AppCompatActivity {

    private EditText mLoginEmail, mLoginPassword;
    private TextView mSignUp;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get current app user
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            startActivity(new Intent(FirebaseLogin.this, SwipePage.class));
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
