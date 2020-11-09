package com.example.adoptme.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adoptme.R;
import com.example.adoptme.SwipePage;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner;
    EditText etFirst, etLast, etPhone, etEmail, etShelter, etAddress;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        etFirst = findViewById(R.id.etFirst);
        etLast = findViewById(R.id.etLast);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etShelter = findViewById(R.id.etShelter);
        etAddress = findViewById(R.id.etAddress);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener((View.OnClickListener) this);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        /**
         * Loads / hides the spinner values according to selection
         * @param parent
         * @param view
         * @param pos
         * @param id
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            // if "adopter" selected:
            parent.getItemAtPosition(pos);
            if (pos == 0){
                etFirst.setVisibility(View.VISIBLE);
                etLast.setVisibility(View.VISIBLE);
                etShelter.setVisibility(View.INVISIBLE);
                etAddress.setVisibility(View.INVISIBLE);
            } else {
                // if "shelter" selected:
                etFirst.setVisibility(View.INVISIBLE);
                etLast.setVisibility(View.INVISIBLE);
                etShelter.setVisibility(View.VISIBLE);
                etAddress.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }

    });

        // Array adapter for default spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.signup_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * On sign up click, handles signing the user in and bringing them
     * to swipe page
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignUp) {
                Intent signup = new Intent(SignUp.this, SwipePage.class);
                startActivity(signup);
            }
        }
}
