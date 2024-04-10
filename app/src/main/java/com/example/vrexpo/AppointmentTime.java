package com.example.vrexpo;

public class AppointmentTime {
    private String time;
    private boolean available;

    public AppointmentTime(String time, boolean available) {
        this.time = time;
        this.available = available;
    }

    public String getTime() {
        return time;
    }

    public boolean isAvailable() {
        return available;
    }
}
