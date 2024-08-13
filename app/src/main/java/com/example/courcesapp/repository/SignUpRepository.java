package com.example.courcesapp.repository;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.courcesapp.model.UserModal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpRepository {

    private static final String TAG = "SignUpRepository";
    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore firestore;

    public SignUpRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public LiveData<Boolean> registerUser(String email, String password, String name, String contact) {
        MutableLiveData<Boolean> registrationStatus = new MutableLiveData<>();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // User registration successful, now store additional user details in Firestore
                        String uId = firebaseAuth.getCurrentUser().getUid();
                        UserModal userModal = new UserModal(uId, name, email, contact);

                        firestore.collection("users").document(uId)
                                .set(userModal)
                                .addOnSuccessListener(aVoid -> {
                                    Log.d(TAG, "User details stored successfully in Firestore");
                                    registrationStatus.setValue(true);
                                })
                                .addOnFailureListener(e -> {
                                    Log.e(TAG, "Failed to store user details in Firestore", e);
                                    registrationStatus.setValue(false);
                                });
                    } else {
                        // Registration failed
                        Log.e(TAG, "User registration failed", task.getException());
                        registrationStatus.setValue(false);
                    }
                });

        return registrationStatus;
    }
}
