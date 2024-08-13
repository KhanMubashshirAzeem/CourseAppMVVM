package com.example.courcesapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.courcesapp.repository.SignInRepository;

public class SignInViewModel extends ViewModel {
    private final SignInRepository repository;

    // Constructor to initialize the repository
    public SignInViewModel() {
        repository = new SignInRepository();
    }

    // Method to initiate user sign-in
    public LiveData<Boolean> signInUser(String email, String password) {
        return repository.signInUser(email, password);
    }

}
