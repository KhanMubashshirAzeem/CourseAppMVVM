package com.example.courcesapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.courcesapp.model.CourseModal;
import com.example.courcesapp.repository.CourseRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final LiveData<List<CourseModal>> coursesLiveData;

    public MainActivityViewModel() {
        CourseRepository repository = new CourseRepository();
        coursesLiveData = repository.getCourses();
    }

    public LiveData<List<CourseModal>> getCourses() {
        return coursesLiveData;
    }

}
