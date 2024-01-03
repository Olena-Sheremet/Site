package com.example.site.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import static org.assertj.core.api.Assertions.assertThat;

;

public class DoctorScheduleTest {

    @Test
    public void testDoctorScheduleCreation() {
        // Arrange
        Doctor doctor = new Doctor();
        String dayOfWeek = "Monday";
        Time startTime = Time.valueOf("08:00:00");
        Time endTime = Time.valueOf("16:00:00");

        // Act
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setDoctor(doctor);
        doctorSchedule.setDayOfWeek(dayOfWeek);
        doctorSchedule.setStartTime(startTime);
        doctorSchedule.setEndTime(endTime);

        // Assert
        assertThat(doctorSchedule).isNotNull();
        assertThat(doctorSchedule.getDoctor()).isEqualTo(doctor);
        assertThat(doctorSchedule.getDayOfWeek()).isEqualTo(dayOfWeek);
        assertThat(doctorSchedule.getStartTime()).isEqualTo(startTime);
        assertThat(doctorSchedule.getEndTime()).isEqualTo(endTime);
    }

    @Test
    public void testDoctorScheduleGettersAndSetters() {
        // Arrange
        DoctorSchedule doctorSchedule = new DoctorSchedule();

        // Act
        Doctor doctor = new Doctor();
        Time startTime = Time.valueOf("09:00:00");
        Time endTime = Time.valueOf("17:00:00");

        doctorSchedule.setDoctor(doctor);
        doctorSchedule.setDayOfWeek("Tuesday");
        doctorSchedule.setStartTime(startTime);
        doctorSchedule.setEndTime(endTime);

        // Assert
        assertThat(doctorSchedule.getDoctor()).isEqualTo(doctor);
        assertThat(doctorSchedule.getDayOfWeek()).isEqualTo("Tuesday");
        assertThat(doctorSchedule.getStartTime()).isEqualTo(startTime);
        assertThat(doctorSchedule.getEndTime()).isEqualTo(endTime);
    }
}
