package com.example.vrexpo;

import java.util.Map;

public class AppointmentModel {
    private Map<String, TimeSlot> slots;
    private String date;

    // Constructor if needed
    public AppointmentModel() {
    }

    public Map<String, TimeSlot> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, TimeSlot> slots) {
        this.slots = slots;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
