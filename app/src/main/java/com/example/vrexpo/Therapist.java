package com.example.vrexpo;

/**
 * Therapist Helper Class
 * Using this class to assist in retrieving Therapist Info from FireBase.
 */
public class Therapist {
    private String name;
    private String specialization;

    public Therapist() {
        // Default constructor required for calls to DataSnapshot.getValue(Therapist.class)
    }

    public Therapist(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }
}
