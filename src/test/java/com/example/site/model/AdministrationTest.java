package com.example.site.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AdministrationTest {

    @Test
    public void testAdministrationCreation() {
        // Arrange
        User user = new User("adminUser", "adminPassword", "admin");
        String firstName = "John";
        String lastName = "Doe";
        String contactNumber = "123456789";
        String email = "john.doe@example.com";
        Date adminHireDate = new Date();
        BigDecimal salary = BigDecimal.valueOf(50000);

        // Act
        Administration administration = new Administration();
        administration.setUser(user);
        administration.setFirstName(firstName);
        administration.setLastName(lastName);
        administration.setContactNumber(contactNumber);
        administration.setEmail(email);
        administration.setAdminHireDate(adminHireDate);
        administration.setSalary(salary);

        // Assert
        assertNotNull(administration);
        assertEquals(user, administration.getUser());
        assertEquals(firstName, administration.getFirstName());
        assertEquals(lastName, administration.getLastName());
        assertEquals(contactNumber, administration.getContactNumber());
        assertEquals(email, administration.getEmail());
        assertEquals(adminHireDate, administration.getAdminHireDate());
        assertEquals(salary, administration.getSalary());
    }

    @Test
    public void testAdministrationGettersAndSetters() {
        // Arrange
        Administration administration = new Administration();

        // Act
        User user = new User("adminUser", "adminPassword", "admin");
        Date adminHireDate = new Date();
        BigDecimal salary = BigDecimal.valueOf(60000);

        administration.setUser(user);
        administration.setFirstName("Jane");
        administration.setLastName("Smith");
        administration.setContactNumber("987654321");
        administration.setEmail("jane.smith@example.com");
        administration.setAdminHireDate(adminHireDate);
        administration.setSalary(salary);

        // Assert
        assertEquals(user, administration.getUser());
        assertEquals("Jane", administration.getFirstName());
        assertEquals("Smith", administration.getLastName());
        assertEquals("987654321", administration.getContactNumber());
        assertEquals("jane.smith@example.com", administration.getEmail());
        assertEquals(adminHireDate, administration.getAdminHireDate());
        assertEquals(salary, administration.getSalary());
    }
}
