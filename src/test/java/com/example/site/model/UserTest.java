package com.example.site.model;

import static org.junit.jupiter.api.Assertions.*;

import com.example.site.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import java.util.Date;

public class UserTest {

    @Mock
    private Date mockedDate;

    @InjectMocks
    private User user;

    @Test
    public void testUserCreation() {
        // Arrange
        String userName = "testUser";
        String userPassword = "testPassword";
        String userType = "regular";
        mockedDate = new Date();

        // Act
        user = new User(userName, userPassword, userType);

        // Assert
        assertNotNull(user);
        assertEquals(userName, user.getUserName());
        assertEquals(userPassword, user.getUserPassword());
        assertEquals(userType, user.getUserType());
        assertNotNull(user.getRegistrationDate());
    }

    @Test
    public void testUserGettersAndSetters() {
        // Arrange
        user = new User();
        String newUserName = "newUserName";
        String newPassword = "newPassword";
        String newUserType = "admin";

        // Act
        user.setUserName(newUserName);
        user.setUserPassword(newPassword);
        user.setUserType(newUserType);

        // Assert
        assertEquals(newUserName, user.getUserName());
        assertEquals(newPassword, user.getUserPassword());
        assertEquals(newUserType, user.getUserType());
    }
}