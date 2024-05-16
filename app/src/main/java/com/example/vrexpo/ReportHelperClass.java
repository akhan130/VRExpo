package com.example.vrexpo;

public class ReportHelperClass {
    String date, therapistName, patientName, treatmentPlan, comments;


    // Required empty constructor for Firebase
    public ReportHelperClass() {
    }

    public ReportHelperClass(String date, String therapistName, String patientName, String phobia, String comments) {
        this.date = date;
        this.therapistName = therapistName;
        this.patientName = patientName;
        this.treatmentPlan = phobia;
        this.comments = comments;
    }

    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTherapistName() {
        return therapistName;
    }

    public void setTherapistName(String therapistName) {
        this.therapistName = therapistName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhobia() {
        return treatmentPlan;
    }

    public void setPhobia(String phobia) {
        this.treatmentPlan = phobia;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
