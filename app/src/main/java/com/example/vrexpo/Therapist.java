package com.example.vrexpo;

/**
 * Therapist Helper Class
 * Using this class to assist in retrieving Therapist Info from FireBase.
 */
public class Therapist {
    private String fullName;
    private String email;
    private String gender;
    private String phoneNumber;
    private String specialization;
    private String password;

    //Find Therapist
    public Therapist(String fullName, String email, String phoneNumber, String specialization) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    //Account Settings
    public Therapist(String fullName, String email, String phoneNumber, String specialization, String gender, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.gender = gender;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

