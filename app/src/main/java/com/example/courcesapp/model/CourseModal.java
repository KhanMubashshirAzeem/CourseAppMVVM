package com.example.courcesapp.model;

public class CourseModal {
    @com.google.firebase.firestore.PropertyName("Course")
    private String Course;

    @com.google.firebase.firestore.PropertyName("ImageLocation")
    private String ImageLocation;

    // Empty constructor required for Firestore
    public CourseModal() {
    }

    public CourseModal(String title, String imageLocation) {
        this.Course = title;
        this.ImageLocation = imageLocation;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        this.Course = course;
    }

    public String getImageLocation() {
        return ImageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.ImageLocation = imageLocation;
    }
}
