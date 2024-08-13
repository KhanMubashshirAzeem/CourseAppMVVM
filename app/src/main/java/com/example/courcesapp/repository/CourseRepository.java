package com.example.courcesapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.courcesapp.model.CourseModal;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final CollectionReference coursesCollection = firestore.collection("Courses");

    // Fetch all courses
    public LiveData<List<CourseModal>> getCourses() {
        MutableLiveData<List<CourseModal>> coursesLiveData = new MutableLiveData<>();

        coursesCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                List<CourseModal> coursesList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("CourseRepository", "Document ID: " + document.getId());
                    CourseModal course = document.toObject(CourseModal.class);
                    Log.d("CourseRepository", "Course title: " + course.getCourse() );
                    coursesList.add(course);
                }

                coursesLiveData.setValue(coursesList);
                Log.d("CourseRepository", "Courses fetched: " + coursesList.size());  // Add this log
            } else {
                coursesLiveData.setValue(null);
                Log.e("CourseRepository", "Error getting documents: ", task.getException());  // Add this log
            }
        });

        return coursesLiveData;
    }

}
