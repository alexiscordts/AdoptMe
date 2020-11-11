package com.example.adoptme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button swipePageButton, likedPets, shelterProfileBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        likedPets = findViewById(R.id.likedPets);
        likedPets.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                likedClick(view);
            }
        });

        swipePageButton = findViewById(R.id.swipePageButton);
        swipePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeClick(view);
            }
        });

        shelterProfileBtn = findViewById(R.id.testShelterProfile);
        shelterProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shelterClick(view);
            }
        });

    }


    private void swipeClick(View view){
        Intent intent = new Intent(this, SwipePage.class);
        startActivity(intent);
    }
    private void likedClick(View view){
        Intent intent = new Intent(this, LikedPets.class);
        startActivity(intent);
    }

    private void shelterClick(View view){
        Intent intent = new Intent(this, ShelterView.class);
        startActivity(intent);
    }
}