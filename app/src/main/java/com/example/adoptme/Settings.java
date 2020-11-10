package com.example.adoptme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adoptme.Login.SignUp;

public class Settings extends AppCompatActivity implements View.OnClickListener {


    Spinner animal_spinner;
    TextView tvPetAge, tvPetType;
    SeekBar age_seek_bar;
    Switch switch_all;
    Button btnSave;

    /**
     * Creates settings instance
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        tvPetAge = findViewById(R.id.etFirst);
        tvPetType = findViewById(R.id.etLast);
        age_seek_bar = findViewById(R.id.age_seek_bar);
        age_seek_bar.setMax(20);
        age_seek_bar.setProgress(5); // 50 default progress value
        int seekBarVal = age_seek_bar.getProgress();
        switch_all = findViewById(R.id.switch_all);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener((View.OnClickListener) this);

        animal_spinner = findViewById(R.id.animal_spinner);
        animal_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Loads / hides the spinner values according to selection
             * @param parent
             * @param view
             * @param pos
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                parent.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        // Array adapter for default spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.settings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animal_spinner.setAdapter(adapter);
    }

    /**
     * On save click, handles redirecting user to swipe page
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSave) {
            Intent save = new Intent(Settings.this, SwipePage.class);
//            updateSwipePageResults()
//            need method call here to save the changes and only show specific preferences
            startActivity(save);
        }
    }


}

