package com.example.vrexpo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class AppointmentModel {
    private Map<String, TimeSlot> slots;
    private String date;

    public AppointmentModel() {
        slots = new HashMap<>();
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

    // Optional: Helper method to get a specific detail from the first available slot
    public String getFirstSlotDetail(Function<TimeSlot, String> extractor) {
        if (!slots.isEmpty()) {
            TimeSlot firstSlot = slots.values().iterator().next();
            return extractor.apply(firstSlot);
        }
        return "Not available";
    }
}
