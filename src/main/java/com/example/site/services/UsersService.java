package com.example.site.services;

import com.example.site.model.Patient;
import com.example.site.model.User;
import com.example.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService  {

    private final UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    public boolean userNameExists(String userName) {
        return userRepository.existsByUserName(userName);
    }

    public User authenticate(String userName, String password) {
        // Здійснити пошук користувача за ім'ям і паролем
        User user = userRepository.findByUserName(userName).orElse(null);

        if (user != null && user.getUserPassword().equals(password)) {;
            return user;
        }
        return null;
    }


}
