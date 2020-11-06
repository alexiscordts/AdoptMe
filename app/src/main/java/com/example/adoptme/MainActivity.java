package com.example.adoptme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button swipePageButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipePageButton = findViewById(R.id.swipePageButton);
        swipePageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                swipeClick(view);

            }
        });
    }
    private void swipeClick(View view){
        Intent intent = new Intent(this, SwipePage.class);
        startActivity(intent);
    }
}