package com.example.site.repository;

import com.example.site.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByDoctorId(int doctorId);
    Doctor findByUserUserId(int userId);
}

