package com.example.createaccount_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String email, name;
    private String password;

    private TextInputEditText emailTextView, passwordTextView, nameTextView;
    private Button registerBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        registerBtn = findViewById(R.id.signUpBtn);
        emailTextView = findViewById(R.id.user_field);
        passwordTextView = findViewById(R.id.password_field);
        nameTextView = findViewById(R.id.name_field);
        progressBar = findViewById(R.id.progressbar);


        registerBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //show progress bar while loading
                register();
            }
        });
    }

    private void register() {
        progressBar.setVisibility(View.VISIBLE);

        name = nameTextView.getText().toString();
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),
                                "Registration successful!",
                                Toast.LENGTH_LONG)
                                .show();

                        // hide the progress bar
                        progressBar.setVisibility(View.GONE);

                        // if the user created intent to login activity
                        // TODO: change intent to login so the user can login with the created account
                        //

                        Intent intent
                                = new Intent(MainActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                    }
                    else {

                        // Registration failed
                        Toast.makeText(
                                getApplicationContext(),
                                "Registration failed!!"
                                        + " Please try again later",
                                Toast.LENGTH_LONG)
                                .show();

                        // hide the progress bar
                        progressBar.setVisibility(View.GONE);
                    }

                }
            });
    }

    private void login (){

        // TODO: also here you got to change the intent to name of the login ativity
        // Note that it left as it is you will stay at the same page when you click

        Intent intent
                = new Intent(MainActivity.this,
                MainActivity.class);
        startActivity(intent);
    }
}
