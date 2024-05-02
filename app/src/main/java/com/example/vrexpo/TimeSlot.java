package com.example.vrexpo;

public class TimeSlot {
    private String date;
    private String appointmentTime;
    private String appointmentStatus;
    private String patientName;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTherapistFullName() {
        return therapistFullName;
    }

    public void setTherapistFullName(String therapistFullName) {
        this.therapistFullName = therapistFullName;
    }
}
