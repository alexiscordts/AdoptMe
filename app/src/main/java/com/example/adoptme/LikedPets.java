package com.example.adoptme;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LikedPets extends AppCompatActivity {

    TextView tvLikedPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liked_pets);

        tvLikedPets = findViewById(R.id.tvLikedPets);
    }
}
