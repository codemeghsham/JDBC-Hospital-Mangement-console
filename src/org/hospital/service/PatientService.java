package org.hospital.service;

import java.util.List;


import org.hospital.model.*;
import org.hospital.repository.*;

public class PatientService {
    private PatientRepository patientRepository;

    public PatientService() {
        this.patientRepository = new PatientRepository();
    }

    public boolean addPatient(PatientModel patient) {
        return patientRepository.addPatient(patient);
    }

    public List<PatientModel> getAllPatients() {
        return patientRepository.getAllPatients();
    }
   
    
    public List<PatientModel> searchPatientsByName(String name) {
        // Call the corresponding method in the PatientRepository to search for patients by name
        return patientRepository.searchPatientsByName(name);
    }

    public boolean deletePatientById(int patientId) {
        return patientRepository.deletePatientById(patientId);
    }

    
}

