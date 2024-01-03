package com.example.site.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @Test
    public void testPatientCreation() {
        // Arrange
        User user = new User("testUser", "testPassword", "regular");
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date();
        String gender = "Male";
        String contactNumber = "123456789";
        String email = "john.doe@example.com";
        String contactAddress = "123 Main Street";

        // Act
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPatientDateOfBirth(dateOfBirth);
        patient.setGender(gender);
        patient.setContactNumber(contactNumber);
        patient.setEmail(email);
        patient.setContactAddress(contactAddress);

        // Assert
        assertNotNull(patient);
        assertEquals(user, patient.getUser());
        assertEquals(firstName, patient.getFirstName());
        assertEquals(lastName, patient.getLastName());
        assertEquals(dateOfBirth, patient.getPatientDateOfBirth());
        assertEquals(gender, patient.getGender());
        assertEquals(contactNumber, patient.getContactNumber());
        assertEquals(email, patient.getEmail());
        assertEquals(contactAddress, patient.getContactAddress());
    }

    @Test
    public void testPatientGettersAndSetters() {
        // Arrange
        Patient patient = new Patient();

        // Act
        patient.setFirstName("Jane");
        patient.setLastName("Doe");
        patient.setGender("Female");
        patient.setContactNumber("987654321");
        patient.setEmail("jane.doe@example.com");
        patient.setContactAddress("456 Second Street");

        // Assert
        assertEquals("Jane", patient.getFirstName());
        assertEquals("Doe", patient.getLastName());
        assertEquals("Female", patient.getGender());
        assertEquals("987654321", patient.getContactNumber());
        assertEquals("jane.doe@example.com", patient.getEmail());
        assertEquals("456 Second Street", patient.getContactAddress());
    }
}