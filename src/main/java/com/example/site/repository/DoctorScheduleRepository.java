package com.example.site.repository;

import com.example.site.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.site.model.DoctorSchedule;
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Integer> {

    List<DoctorSchedule> findByDoctor(Doctor doctor);

    List<DoctorSchedule> findByDoctor_DoctorId(int doctorId);
}


