package com.example.vrexpo;

public class AppointmentModel {
    private String appointmentTime;
    private String appointmentStatus;
    private String patientName;
    private String therapistFullName;
    private long date;

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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
