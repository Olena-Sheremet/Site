package com.example.site.services;

import com.example.site.model.Patient;
import com.example.site.model.User;
import com.example.site.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientsService {

    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientsService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }
    public void deletePatient(int patientId) {
        patientsRepository.deleteById(patientId);
    }
    public List<Patient> getAllPatients() {
        return patientsRepository.findAll();
    }

    public Patient getPatientByUser(User user) {
        return patientsRepository.findByUser(user);
    }

    public void savePatient(Patient patient) {
        patientsRepository.save(patient);
    }

    public void deletePatientById(int id) {
        patientsRepository.deleteById(id);
    }

    public Optional<Patient> getPatientById(int id) {
        return patientsRepository.findById(id);
    }
}