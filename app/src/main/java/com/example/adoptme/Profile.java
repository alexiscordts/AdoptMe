package com.example.adoptme;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adoptme.R;

public class Profile extends AppCompatActivity {

    TextView tvProfile, tvName, tvEmail, tvPhone;
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        tvProfile = findViewById(R.id.tvProfile);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);

        imgProfile = findViewById(R.id.imgProfile);
    }
}
