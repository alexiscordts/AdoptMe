package com.example.adoptme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adoptme.Accounts.Adopter;
import com.example.adoptme.Accounts.Animal;
import com.example.adoptme.Accounts.AnimalShelter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseCreateAccount extends AppCompatActivity {

    private EditText mName, mEmail, mPassword, mAcctType;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_create_account);

        mName = findViewById(R.id.createAccountInputName);
        mEmail = findViewById(R.id.createAccountInputEmail);
        mPassword = findViewById(R.id.createAccountInputPassword);
        mAcctType = findViewById(R.id.createAccountInputAcctType);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
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
                    if(acctType.equalsIgnoreCase("adopter")){
                        Adopter adopter = new Adopter(uid, name, "", addTempLikedList());
                        mDatabase.child("users").child(uid).setValue(adopter);

                        startActivity(new Intent(FirebaseCreateAccount.this, SwipePage.class));
                    }else{
                        AnimalShelter shelter = new AnimalShelter(uid, name, "", null, new ArrayList<Animal>());
                        mDatabase.child("users").child(uid).setValue(shelter);

                        startActivity(new Intent(FirebaseCreateAccount.this, ShelterView.class));
                    }
                    finish();
                }
            }
        });
    }

    public ArrayList<Animal> addTempLikedList(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Eevee",R.drawable.eevee, 8, "Dog"));
        animals.add(new Animal("Iron",R.drawable.iron, 3, "Dog"));

        return animals;
    }

}