package com.example.vrexpo;

import android.content.Intent;

public class AndroidUtil {
    public static void passUserModelAsIntent(Intent intent, PatientModel model) {
        intent.putExtra("name",model.getName());
        intent.putExtra("phone",model.getPhone());
    }

    public static PatientModel getPatientModelFromIntent(Intent intent){
        PatientModel patientModel = new PatientModel();
        patientModel.setName(intent.getStringExtra("name"));
        patientModel.setPhone(intent.getStringExtra("phone"));
        return patientModel;
    }
}
