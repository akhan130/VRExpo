package com.example.vrexpo;

public class Availability {
    private String date;
    private String timeRange;

    public Availability() {
        // Default constructor required for Firebase
    }

    // Constructor with parameters
    public Availability(String date, String timeRange) {
        this.date = date;
        this.timeRange = timeRange;
    }

    // Getters and setters for the properties
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }
}
