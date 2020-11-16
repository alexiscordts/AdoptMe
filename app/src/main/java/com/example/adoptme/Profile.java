package com.example.adoptme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adoptme.Accounts.Adopter;
import com.example.adoptme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView tvProfile;
    EditText mEditName, mEditPhone;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    protected Adopter adopter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        tvProfile = findViewById(R.id.tvProfile);

        mEditName = findViewById(R.id.editName);
        mEditPhone = findViewById(R.id.editPhone);

        auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adopter = dataSnapshot.getValue(Adopter.class);
                Toast.makeText(Profile.this, "Welcome: " + adopter.getName(), Toast.LENGTH_SHORT).show();


                mEditName.setText(adopter.getName());
                mEditPhone.setText(adopter.getPhone());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mDatabase.addListenerForSingleValueEvent(valueEventListener);

    }

    public void onDeleteClick(View v){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren()){
                    d.getRef().removeValue();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mDatabase.addListenerForSingleValueEvent(valueEventListener);

        if (user != null) {
            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Profile.this, "Your profile is deleted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Profile.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(Profile.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    public void onSaveClick(View v) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adopter.setName(mEditName.getText().toString());
                adopter.setPhone(mEditPhone.getText().toString());
                mDatabase.setValue(adopter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mDatabase.addListenerForSingleValueEvent(valueEventListener);

    }
}
