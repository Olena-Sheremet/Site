package com.example.site.services;

import com.example.site.model.Patient;
import com.example.site.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.site.repository.PrescriptionRepository;


@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public List<Prescription> findByPatient(Patient patient) {
        return prescriptionRepository.findByPatient(patient);
    }
    public void addPrescription (Prescription prescription){
        prescriptionRepository.save(prescription);
    }
    public Optional<Prescription> getPrescriptionById(int id) {
        return prescriptionRepository.findById(id);
    }

    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public void deletePrescriptionById(int id) {
        prescriptionRepository.deleteById(id);
    }


}
