package com.example.site.model;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class AppointmentScheduleTest {

    @Test
    public void testAppointmentScheduleCreation() {
        // Arrange
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        Time appointmentTime = Time.valueOf("10:00:00");
        String statusSchedule = "Scheduled";

        // Act
        AppointmentSchedule appointmentSchedule = new AppointmentSchedule();
        appointmentSchedule.setPatient(patient);
        appointmentSchedule.setDoctor(doctor);
        appointmentSchedule.setAppointmentTime(appointmentTime);
        appointmentSchedule.setStatusSchedule(statusSchedule);

        // Assert
        assertNotNull(appointmentSchedule);
        assertEquals(patient, appointmentSchedule.getPatient());
        assertEquals(doctor, appointmentSchedule.getDoctor());
        assertEquals(appointmentTime, appointmentSchedule.getAppointmentTime());
        assertEquals(statusSchedule, appointmentSchedule.getStatusSchedule());
    }

    @Test
    public void testAppointmentScheduleGettersAndSetters() {
        // Arrange
        AppointmentSchedule appointmentSchedule = new AppointmentSchedule();

        // Act
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        Time appointmentTime = Time.valueOf("10:00:00");
        String statusSchedule = "Scheduled";

        appointmentSchedule.setPatient(patient);
        appointmentSchedule.setDoctor(doctor);
        appointmentSchedule.setAppointmentTime(appointmentTime);
        appointmentSchedule.setStatusSchedule(statusSchedule);

        // Assert
        assertEquals(patient, appointmentSchedule.getPatient());
        assertEquals(doctor, appointmentSchedule.getDoctor());
        assertEquals(appointmentTime, appointmentSchedule.getAppointmentTime());
        assertEquals(statusSchedule, appointmentSchedule.getStatusSchedule());
    }
}