package com.example.adoptme.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.adoptme.R;
import com.example.adoptme.SwipePage;

import androidx.appcompat.app.AppCompatActivity;

/**
 * An activity that handles user login
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    private AwesomeValidation validate;
    EditText username;
    EditText password;
    Button btnLogin, btnSignUp;
    ImageView imgDogPic;

    /**
     * Creates login instance
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        imgDogPic = findViewById(R.id.imgDogPic);

        // initializing awesomeValidation library for form validation using regex
        validate = new AwesomeValidation(ValidationStyle.BASIC);

        validate.addValidation(this, R.id.username, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.userNameError);
        // Password regex: at least one lower case, one upper case, and one number - must be at least 8 characters long
        validate.addValidation(this, R.id.password, "^(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{4,}$", R.string.passwordError);

        btnLogin= findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

    }


    /**
     * Checks if the username and password fields meet syntax requirements
     *
     * @return True if syntax is valid, False is syntax is invalid
     */
    public boolean validateForm() {
        return validate.validate();
    }

    /**
     * On login click, handles valdidation if info meets requirements
     * and then signs the user in
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            if (validateForm()) {
                Intent login = new Intent(Login.this, SwipePage.class);
                startActivity(login);
            }
        }
        else if (view.getId() == R.id.btnSignUp) {
            Intent reg = new Intent(Login.this, SignUp.class);
            startActivity(reg);
        }
    }

}