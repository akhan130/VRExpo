package com.example.vrexpo;

import java.util.ArrayList;
import java.util.List;

public class MatchedData {

    private static String patientCondition;
    private static String patientPhobia;
    private static String patientPTSD;
    private static String therapistSpecialization;
    private static List<String> matchedTherapistIds = new ArrayList<>();

    // Setters
    public static void setPatientCondition(String condition) {
        patientCondition = condition;
    }

    public static void setPatientPhobia(String phobia) {
        patientPhobia = phobia;
    }

    public static void setPatientPTSD(String ptsd) {
        patientPTSD = ptsd;
    }

    public static void setTherapistSpecialization(String specialization) {
        therapistSpecialization = specialization;
    }

    public static void setMatchedTherapistIds(List<String> therapistIds) {
        matchedTherapistIds = therapistIds;
    }

    // Getters
    public static String getPatientCondition() {
        return patientCondition;
    }

    public static String getPatientPhobia() {
        return patientPhobia;
    }

    public static String getPatientPTSD() {
        return patientPTSD;
    }

    public static String getTherapistSpecialization() {
        return therapistSpecialization;
    }

    public static List<String> getMatchedTherapistIds() {
        return matchedTherapistIds;
    }
}
