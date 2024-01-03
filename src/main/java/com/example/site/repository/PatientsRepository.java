package com.example.site.repository;

import com.example.site.model.Doctor;
import com.example.site.model.Patient;
import com.example.site.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface PatientsRepository extends JpaRepository<Patient, Integer> {
    Patient findByUser(User user);


}