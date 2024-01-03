package com.example.site.repository;

import com.example.site.model.Patient;
import com.example.site.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
    List<Prescription> findByPatient(Patient patient);

}

