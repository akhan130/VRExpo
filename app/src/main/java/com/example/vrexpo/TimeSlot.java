package com.example.vrexpo;

public class TimeSlot {
    private String date;
    private String appointmentTime;
    private String appointmentStatus;
    private String patient_name;
    private String therapistFullName;

    public TimeSlot() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getTherapistFullName() {
        return therapistFullName;
    }

    public void setTherapistFullName(String therapistFullName) {
        this.therapistFullName = therapistFullName;
    }
}
