package com.example.vrexpo;

import com.google.firebase.database.PropertyName;

public class TherapistAccountHelperClass {
    String name, specialization, gender, email, phone, password;

    public TherapistAccountHelperClass(){
    }

    public TherapistAccountHelperClass(String name, String specialization, String gender, String email, String phone, String password){
        this.name = name;
        this.specialization = specialization;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @PropertyName("therapist_fullName")
    public String getName() {
        return name;
    }

    @PropertyName("therapist_fullName")
    public void setName(String name) {
        this.name = name;
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

    @PropertyName("therapist_email")
    public String getEmail() {
        return email;
    }

    @PropertyName("therapist_email")
    public void setEmail(String email) {
        this.email = email;
    }

    @PropertyName("therapist_phoneNumber")
    public String getPhone() {
        return phone;
    }

    @PropertyName("therapist_phoneNumber")
    public void setPhone(String phone) {
        this.phone = phone;
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
