-- Patient --
CREATE TABLE VRExpo.PatientInfo(
    patient_id INT AUTO_INCREMENT,
    patient_fullname VARCHAR(255),
    patient_dob VARCHAR(255),
    patient_gender VARCHAR(255),
    patient_email VARCHAR(255),
    patient_phoneNumber INT,
    patient_address VARCHAR(255),
    patient_emergencyContactName VARCHAR (255),
    patient_emergencyContactPhoneNumber INT,
    patient_insurance VARCHAR(255),
    patient_username VARCHAR(255),
    patient_password VARCHAR(255),
    PRIMARY KEY (patient_id)
);

CREATE TABLE VRExpo.PatientHistory(
	patient_phobia VARCHAR(255),
    patient_medications VARCHAR (255),
    patient_allergies VARCHAR(255),
    patient_medicalConditions VARCHAR(255),
    patient_symptoms VARCHAR(255),
    patient_familyHistory VARCHAR(255),
    patient_previousTreatments VARCHAR(255),
    patient_id INT,
	FOREIGN KEY (patient_id) REFERENCES PatientInfo(patient_id),
    PRIMARY KEY (patient_id)
);

-- Therapist --
CREATE TABLE VRExpo.TherapistInfo(
    therapist_id INT AUTO_INCREMENT,
    therapist_fullname VARCHAR(255),
    therapist_dob VARCHAR(255),
    therapist_gender VARCHAR(255),
    therapist_email VARCHAR(255),
    therapist_phoneNumber INT,
    therapist_specialization VARCHAR(255),
    therapist_username VARCHAR(255),
    therapist_password VARCHAR(255),
    PRIMARY KEY (therapist_id)
    );
    
CREATE TABLE VRExpo.TherapistHistory(
	therapist_education VARCHAR(255),
    therapist_degree VARCHAR(255),
    therapist_certification VARCHAR(255),
    therapist_workHistory VARCHAR(255),
    therapist_id INT,
	FOREIGN KEY (therapist_id) REFERENCES TherapistInfo(therapist_id),
    PRIMARY KEY (therapist_id)
);

-- Meeting --
CREATE TABLE VRExpo.Appointments(
	appointment_id INT AUTO_INCREMENT,
    appointment_date VARCHAR(255),
    appointment_time VARCHAR(255),
    appointment_location VARCHAR(255),
    appointment_status VARCHAR(255),
    patient_id INT,
    therapist_id INT,
	FOREIGN KEY (patient_id) REFERENCES PatientInfo(patient_id),
    FOREIGN KEY(therapist_id) REFERENCES TherapistInfo(therapist_id),
    PRIMARY KEY (appointment_id)
);
