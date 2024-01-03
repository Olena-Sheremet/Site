package com.example.site.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {

    @Test
    public void testDepartmentCreation() {
        // Arrange
        Doctor headDoctor = new Doctor();
        headDoctor.setFirstName("John");
        headDoctor.setLastName("Doe");
        headDoctor.setSpecialty("Cardiologist");

        String departmentName = "Cardiology";

        // Act
        Department department = new Department();
        department.setHeadDoctor(headDoctor);
        department.setDepartmentName(departmentName);

        // Assert
        assertNotNull(department);
        assertNotNull(department.getDepartmentId()); // Assuming id is generated
        assertEquals(headDoctor, department.getHeadDoctor());
        assertEquals(departmentName, department.getDepartmentName());
    }

    @Test
    public void testDepartmentGettersAndSetters() {
        // Arrange
        Department department = new Department();

        // Act
        Doctor headDoctor = new Doctor();
        department.setHeadDoctor(headDoctor);
        department.setDepartmentName("Neurology");

        // Assert
        assertEquals(headDoctor, department.getHeadDoctor());
        assertEquals("Neurology", department.getDepartmentName());
    }
}