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
import com.example.courcesapp.viewmodel.SignInViewModel;
import com.example.courcesapp.databinding.ActivitySignInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    private SignInViewModel signInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        currentUserHandle();

        signInMethod();

        goToSignUp();

    }

    private void currentUserHandle() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void goToSignUp() {
        // Set onClickListener for the TextView to switch to SignUpActivity
        binding.signInToSignUp.setOnClickListener(v -> {
            // Create an intent to start the SignUpActivity
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
            // Optionally, finish the SignInActivity so the user can't go back to it using the back button
            finish();
        });
    }

    private void signInMethod() {
        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailSignIn.getText().toString().trim();
                String password = binding.passwordSignIn.getText().toString().trim();

                // Call ViewModel's signInUser method to initiate the sign-in process
                signInViewModel.signInUser(email, password).observe(SignInActivity.this, isSuccess -> {
                    if (isSuccess) {
                        // Sign-in successful, navigate to the main screen or dashboard
                        Toast.makeText(SignInActivity.this, "Sign-In Successful", Toast.LENGTH_SHORT).show();
                        // Intent to navigate to the main activity (replace MainActivity with your activity)
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();  // Finish SignInActivity so the user can't go back to it using the back button
                    } else {
                        // Sign-in failed, show a message to the user
                        Toast.makeText(SignInActivity.this, "Sign-In Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}