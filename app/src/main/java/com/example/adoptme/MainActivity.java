package com.example.adoptme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button mLoginButton ;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase, mUserReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginButton = (Button) findViewById(R.id.loginButton);


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
                    if(type.equalsIgnoreCase("adopter")){
                        startActivity(new Intent(MainActivity.this, SwipePage.class));
                    }else{
                        startActivity(new Intent(MainActivity.this, ShelterView.class));
                    }
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };

            mUserReference.addListenerForSingleValueEvent(valueEventListener);
        }

    }


    public void loginClick(View view){
        Intent intent = new Intent(this, FirebaseLogin.class);
        startActivity(intent);
    }
}