package com.example.site.services;

import com.example.site.model.AppointmentSchedule;
import com.example.site.model.Patient;
import com.example.site.model.Prescription;
import com.example.site.repository.AppointmentScheduleRepository;
import com.example.site.repository.DoctorsRepository;
import com.example.site.repository.PatientsRepository;
import com.example.site.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentScheduleService {

    private final AppointmentScheduleRepository appointmentScheduleRepository;
    private final PatientsRepository patientsRepository;
    private final DoctorsRepository doctorsRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public AppointmentScheduleService(AppointmentScheduleRepository appointmentScheduleRepository,
                                      PatientsRepository patientsRepository,
                                      PrescriptionRepository prescriptionRepository,
                                      DoctorsRepository doctorsRepository
    ) {
        this.appointmentScheduleRepository = appointmentScheduleRepository;
        this.patientsRepository = patientsRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.doctorsRepository = doctorsRepository;
    }

    public List<AppointmentSchedule> getAllAppointmentSchedules() {
        return appointmentScheduleRepository.findAll();
    }



    public List<AppointmentSchedule> getDoctorAppointmentsAndPatientsAndPrescriptions(int doctorId) {

        // Отримання пацієнтів цього лікаря
        List<AppointmentSchedule> appointments = appointmentScheduleRepository.findByDoctor(doctorsRepository.findByDoctorId(doctorId));

        // Отримання направлень для кожного запису AppointmentSchedule
        for (AppointmentSchedule appointment : appointments) {
            // Отримання пацієнта для кожного запису
            Patient patient = appointment.getPatient();
        }

        return appointments;
    }

    public Optional<AppointmentSchedule> getAppointmentById(int id) {
        return appointmentScheduleRepository.findById(id);
    }

    public AppointmentSchedule saveAppointment(AppointmentSchedule appointment) {
        return appointmentScheduleRepository.save(appointment);
    }

    public void deleteAppointmentById(int id) {
        appointmentScheduleRepository.deleteById(id);
    }

    public List<AppointmentSchedule> getScheduledAppointmentsForPatient(Patient patient) {
        return appointmentScheduleRepository.findByPatientAndStatusSchedule(patient, "scheduled");
    }
}
