package com.example.site.services;

import com.example.site.model.Doctor;
import com.example.site.model.DoctorSchedule;
import com.example.site.repository.DoctorScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;
    @Autowired
    public DoctorScheduleService(DoctorScheduleRepository doctorScheduleRepository) {
        this.doctorScheduleRepository = doctorScheduleRepository;
    }

    public List<DoctorSchedule> getDoctorSchedulesByDoctor(Doctor doctor) {
        return doctorScheduleRepository.findByDoctor(doctor);
    }
    public List<DoctorSchedule> getDoctorSchedulesByDoctorId(int doctorId) {
        return doctorScheduleRepository.findByDoctor_DoctorId(doctorId);
    }

    public Optional<DoctorSchedule> getDoctorScheduleById(int scheduleId) {
        return doctorScheduleRepository.findById(scheduleId);
    }
    public List<DoctorSchedule> getAllDoctorSchedules() {
        return doctorScheduleRepository.findAll();
    }

    public void saveDoctorSchedule(DoctorSchedule doctorSchedule) {
        doctorScheduleRepository.save(doctorSchedule);
    }

    public void deleteDoctorScheduleById(int scheduleId) {
        doctorScheduleRepository.deleteById(scheduleId);
    }



}
