package com.example.site.services;

import com.example.site.model.Doctor;
import com.example.site.repository.DoctorsRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorsService {

    private final DoctorsRepository doctorsRepository;

    @Autowired
    public DoctorsService(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }

    public Optional<Doctor> getDoctorById(int id) {
        return doctorsRepository.findById(id);
    }

    public Doctor getDoctorByUserId(int userId) {
        return doctorsRepository.findByUserUserId(userId);
    }

    public List<Doctor> getAllDoctors() {
        return doctorsRepository.findAll();
    }

    public void deleteDoctor(int doctorId) {
        doctorsRepository.deleteById(doctorId);
    }

    public Doctor saveDoctor(Doctor doctor) {
        if (doctor.getDoctorId() == 0) {
            // Новий лікар
            return doctorsRepository.save(doctor);
        } else {
            // Редагування існуючого лікаря
            Optional<Doctor> existingDoctor = doctorsRepository.findById(doctor.getDoctorId());
            if (existingDoctor.isPresent()) {
                Doctor editedDoctor = existingDoctor.get();
                editedDoctor.setFirstName(doctor.getFirstName());
                editedDoctor.setLastName(doctor.getLastName());
                editedDoctor.setSpecialty(doctor.getSpecialty());
                editedDoctor.setContactNumber(doctor.getContactNumber());
                editedDoctor.setDoctorHireDate(doctor.getDoctorHireDate());
                editedDoctor.setSalary(doctor.getSalary());
                editedDoctor.setDepartment(doctor.getDepartment());

                return doctorsRepository.save(editedDoctor);
            } else {
                // Обробка випадку, коли лікаря з ідентифікатором не знайдено
                throw new EntityNotFoundException("Лікаря з ідентифікатором " + doctor.getDoctorId() + " не знайдено.");
            }
        }
    }

    public void deleteDoctorById(int id) {
        doctorsRepository.deleteById(id);
    }
}