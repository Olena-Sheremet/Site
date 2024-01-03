package com.example.site.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PrescriptionTest {
    @Mock
    private Doctor doctor;
    @Mock
    private Patient patient;
    @Test
    public void testPrescriptionCreation() {
        // Arrange
        doctor=new Doctor();
        patient=new Patient();
        String medication = "Aspirin";
        String dosage = "1 tablet";
        String instructions = "Take with water";

        // Act
        Prescription prescription = new Prescription();
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);

        prescription.setMedication(medication);
        prescription.setDosage(dosage);
        prescription.setInstructions(instructions);

        // Assert
        assertNotNull(prescription);
        assertEquals(doctor, prescription.getDoctor());
        assertEquals(patient, prescription.getPatient());
        assertEquals(medication, prescription.getMedication());
        assertEquals(dosage, prescription.getDosage());
        assertEquals(instructions, prescription.getInstructions());
    }

    @Test
    public void testPrescriptionGettersAndSetters() {
        // Arrange
        Prescription prescription = new Prescription();

        // Act
        doctor=new Doctor();
        patient=new Patient();

        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setMedication("Ibuprofen");
        prescription.setDosage("2 tablets");
        prescription.setInstructions("Take with food");

        // Assert
        assertEquals(doctor, prescription.getDoctor());
        assertEquals(patient, prescription.getPatient());
        assertEquals("Ibuprofen", prescription.getMedication());
        assertEquals("2 tablets", prescription.getDosage());
        assertEquals("Take with food", prescription.getInstructions());
    }
}