package com.example.vrexpo;

/**
 * Therapist Helper Class
 * Using this class to assist in retrieving Therapist Info from FireBase.
 */
public class Therapist {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String specialization;

    public Therapist(String fullName, String email, String phoneNumber, String specialization) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
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
}

