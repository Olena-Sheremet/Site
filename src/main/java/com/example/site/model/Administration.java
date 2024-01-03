package com.example.site.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "administrations")
public class Administration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "admin_first_name", nullable = false)
    private String firstName;

    @Column(name = "admin_last_name", nullable = false)
    private String lastName;

    @Column(name = "admin_contact_number")
    private String contactNumber;

    @Column(name = "admin_email")
    private String email;

    @Column(name = "admin_hire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adminHireDate;

    @Column(name = "admin_salary")
    private java.math.BigDecimal salary;

    public Administration() {
        // default constructor
    }

    // Getters and setters

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAdminHireDate() {
        return adminHireDate;
    }

    public void setAdminHireDate(Date hireDate) {
        this.adminHireDate = hireDate;
    }

    public java.math.BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(java.math.BigDecimal salary) {
        this.salary = salary;
    }
}

