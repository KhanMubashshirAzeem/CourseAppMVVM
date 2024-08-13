package com.example.courcesapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.courcesapp.repository.SignUpRepository;

public class SignUpViewModel extends ViewModel {

    private final SignUpRepository repository;

    public SignUpViewModel() {
        repository = new SignUpRepository();
    }

    public LiveData<Boolean> registerUser(String email, String password, String name, String contact) {
        return repository.registerUser(email, password, name, contact);
    }
}

