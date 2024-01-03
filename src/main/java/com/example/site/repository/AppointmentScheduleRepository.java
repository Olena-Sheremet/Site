package com.example.site.repository;

import com.example.site.model.AppointmentSchedule;
import com.example.site.model.Doctor;
import com.example.site.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentScheduleRepository extends JpaRepository<AppointmentSchedule, Integer> {
    List<AppointmentSchedule> findByDoctor(Doctor doctor);
    List<AppointmentSchedule> findByPatientAndStatusSchedule(Patient patient, String statusSchedule);

}

