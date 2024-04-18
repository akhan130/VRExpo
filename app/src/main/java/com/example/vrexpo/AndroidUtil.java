package com.example.vrexpo;

import android.content.Intent;

public class AndroidUtil {
    public static void passUserModelAsIntent(Intent intent, PatientModel model) {
        intent.putExtra("name",model.getName());
        intent.putExtra("phone",model.getPhone());
    }

    public static void passTherapistModelAsIntent(Intent intent, Therapist model) {
        intent.putExtra("name",model.getFullName());
        intent.putExtra("phone",model.getPhoneNumber());
    }

    public static PatientModel getPatientModelFromIntent(Intent intent){
        PatientModel patientModel = new PatientModel();
        patientModel.setName(intent.getStringExtra("name"));
        patientModel.setPhone(intent.getStringExtra("phone"));
        return patientModel;
    }
}
