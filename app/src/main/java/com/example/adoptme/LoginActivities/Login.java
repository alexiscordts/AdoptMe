package com.example.adoptme.LoginActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.adoptme.R;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private AwesomeValidation validate;
    private ProgressDialog pDialog;
    EditText username;
    EditText password;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        // initializing awesomeValidation library for form validation using regex
        validate = new AwesomeValidation(ValidationStyle.BASIC);

        validate.addValidation(this, R.id.username, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.userNameError);
        // Password regex: at least one lower case, one upper case, and one number - must be at least 8 characters long
        validate.addValidation(this, R.id.password, "^(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{4,}$", R.string.passwordError);

//        btnLogin.setOnClickListener(this);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, SignUp.class);
                Login.this.startActivity(register);

            }
        });
    }
    /**
     * Checks if the username and password fields meet syntax requirements
     *
     * @return True if syntax is valid, False is syntax is invalid
     */
    public boolean validateForm() {
        return validate.validate();
    }

}
