package com.example.adoptme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SwipeScreen extends AppCompatActivity {

    TextView tvSwipeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_screen);

        tvSwipeTitle = findViewById(R.id.tvSwipeTitle);
    }

}