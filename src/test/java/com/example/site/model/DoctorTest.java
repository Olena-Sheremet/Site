package com.example.site.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {

    @Test
    public void testDoctorCreation() {
        // Arrange
        User user = new User("testUser", "testPassword", "doctor");
        Department department = new Department();
        Date hireDate = new Date();

        // Act
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setFirstName("John");
        doctor.setLastName("Doe");
        doctor.setSpecialty("Cardiology");
        doctor.setContactNumber("123-456-7890");
        doctor.setDoctorHireDate(hireDate);
        doctor.setSalary(new BigDecimal("100000.00"));
        doctor.setDepartment(department);

        // Assert
        assertNotNull(doctor);
        assertEquals(user, doctor.getUser());
        assertEquals("John", doctor.getFirstName());
        assertEquals("Doe", doctor.getLastName());
        assertEquals("Cardiology", doctor.getSpecialty());
        assertEquals("123-456-7890", doctor.getContactNumber());
        assertEquals(hireDate, doctor.getDoctorHireDate());
        assertEquals(new BigDecimal("100000.00"), doctor.getSalary());
        assertEquals(department, doctor.getDepartment());
    }

    @Test
    public void testDoctorGettersAndSetters() {
        // Arrange
        Doctor doctor = new Doctor();
        User user = new User();
        Department department = new Department();
        Date hireDate = new Date();

        // Act
        doctor.setUser(user);
        doctor.setFirstName("Jane");
        doctor.setLastName("Smith");
        doctor.setSpecialty("Pediatrics");
        doctor.setContactNumber("987-654-3210");
        doctor.setDoctorHireDate(hireDate);
        doctor.setSalary(new BigDecimal("90000.00"));
        doctor.setDepartment(department);

        // Assert
        assertEquals(user, doctor.getUser());
        assertEquals("Jane", doctor.getFirstName());
        assertEquals("Smith", doctor.getLastName());
        assertEquals("Pediatrics", doctor.getSpecialty());
        assertEquals("987-654-3210", doctor.getContactNumber());
        assertEquals(hireDate, doctor.getDoctorHireDate());
        assertEquals(new BigDecimal("90000.00"), doctor.getSalary());
        assertEquals(department, doctor.getDepartment());
    }
}