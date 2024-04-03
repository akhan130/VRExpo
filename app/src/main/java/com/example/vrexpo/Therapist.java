package com.example.vrexpo;
import com.google.firebase.database.PropertyName;

/**
 * Therapist Helper Class
 * Using this class to assist in retrieving Therapist Info from FireBase.
 * TherapistModel
 */

public class Therapist {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String specialization;
    private String gender;
    private String password;

    public Therapist() {
        // Default constructor required for Firebase
    }

    // Use PropertyName annotation to specify the field names in Firebase
    @PropertyName("therapist_fullName")
    public String getFullName() {
        return fullName;
    }

    @PropertyName("therapist_fullName")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @PropertyName("therapist_email")
    public String getEmail() {
        return email;
    }

    @PropertyName("therapist_email")
    public void setEmail(String email) {
        this.email = email;
    }

    @PropertyName("therapist_phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @PropertyName("therapist_phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @PropertyName("therapist_specialization")
    public String getSpecialization() {
        return specialization;
    }

    @PropertyName("therapist_specialization")
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @PropertyName("therapist_gender")
    public String getGender() {
        return gender;
    }

    @PropertyName("therapist_gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @PropertyName("therapist_password")
    public String getPassword() {
        return password;
    }

    @PropertyName("therapist_password")
    public void setPassword(String password) {
        this.password = password;
    }
}
