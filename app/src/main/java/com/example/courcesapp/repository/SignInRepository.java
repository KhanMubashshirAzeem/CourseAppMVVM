package com.example.courcesapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;

public class SignInRepository {

    private final FirebaseAuth firebaseAuth;

    // Constructor to initialize FirebaseAuth
    public SignInRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Method to sign in an existing user
    public LiveData<Boolean> signInUser(String email, String password) {
        MutableLiveData<Boolean> signInStatus = new MutableLiveData<>();

        // Sign in the user with email and password
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Sign-in successful
                signInStatus.setValue(true);
            } else {
                // Sign-in failed
                signInStatus.setValue(false);
            }
        });

        return signInStatus;
    }
}
