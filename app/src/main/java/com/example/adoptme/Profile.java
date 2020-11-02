package com.example.adoptme;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    TextView tvProfile;
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        tvProfile = findViewById(R.id.tvProfile);
        imgProfile = findViewById(R.id.imgProfile);
    }
}
