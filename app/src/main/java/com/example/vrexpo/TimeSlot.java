package com.example.vrexpo;

public class TimeSlot {
    private String date;
    private String appointmentTime;
    private String appointment_status;
    private String patient_name;
    private String therapist_fullName;

    public TimeSlot() {
    }

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
        return appointment_status;
    }

    public void setAppointmentStatus(String appointment_status) {
        this.appointment_status = appointment_status;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getTherapist_fullName() {
        return therapist_fullName;
    }

    public void setTherapist_fullName(String therapist_fullName) {
        this.therapist_fullName = therapist_fullName;
    }
}
