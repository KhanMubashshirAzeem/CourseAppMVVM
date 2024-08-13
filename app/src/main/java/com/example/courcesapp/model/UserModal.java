package com.example.courcesapp.model;

public class UserModal {

    private String uid;
    private String name;
    private String email;
    private String contactNum;

    public UserModal() {
        // Empty constructor require for Firebase Firestore
    }

    public UserModal(String uid, String name, String email, String contactNum) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.contactNum = contactNum;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
}
