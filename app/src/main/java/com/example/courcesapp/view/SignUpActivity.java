package com.example.courcesapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.courcesapp.R;
import com.example.courcesapp.viewmodel.SignUpViewModel;
import com.example.courcesapp.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private SignUpViewModel signUpViewModel;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentUserHandle();
        // Set onClickListener for the Register button
        registerBtnMethod();

        goToSignIn();

    }

    private void currentUserHandle() {
        if (user != null) {
            // Optionally handle user data here
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }


    private void goToSignIn() {
        // Set onClickListener for the TextView to switch to SignInActivity
        binding.signUpToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the SignInActivity
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                // Optionally, finish the SignUpActivity so the user can't go back to it using the back button
                finish();
            }
        });
    }

    private void registerBtnMethod() {
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailSignUp.getText().toString().trim();
                String password = binding.passwordSignUp.getText().toString().trim();
                String name = binding.nameSignUp.getText().toString().trim();
                String contact = binding.contactSignUp.getText().toString().trim();

                signUpViewModel.registerUser(email, password, name, contact).observe(SignUpActivity.this, isSuccess -> {
                    if (isSuccess) {
                        // Registration successful, handle UI accordingly
                        Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                        finish();
                    } else {
                        // Registration failed, handle UI accordingly
                        Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}