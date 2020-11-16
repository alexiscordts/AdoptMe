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

import com.example.adoptme.Accounts.Adopter;

public class Settings extends AppCompatActivity implements View.OnClickListener {


    Spinner animal_spinner;
    TextView tvPetAge, tvPetType, seekBarVal1, seekBarVal2;
    SeekBar age_seek_bar_min, age_seek_bar_max;
    Switch switch_all;
    Button btnSave;
    Adopter adopter;

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
        age_seek_bar_min = findViewById(R.id.age_seek_bar_min);
        age_seek_bar_max = findViewById(R.id.age_seek_bar_max);
        age_seek_bar_min.setMax(25);
        age_seek_bar_max.setMax(25);
        age_seek_bar_min.setProgress(1); // 5 default progress value
        age_seek_bar_max.setProgress(1); // 5 default progress value
        age_seek_bar_min.getProgress();
        age_seek_bar_max.getProgress();
        seekBarVal1 = findViewById(R.id.seekBarVal1);
        seekBarVal2 = findViewById(R.id.seekBarVal2);
        age_seek_bar_min.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBarVal1.setText(String.valueOf(progress));
                adopter.changeMinAge(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        age_seek_bar_max.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBarVal2.setText(String.valueOf(progress));
                adopter.changeMaxAge(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

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
//            need method call here to save the changes and only show specific preferences?
            startActivity(save);
        }
    }


}

