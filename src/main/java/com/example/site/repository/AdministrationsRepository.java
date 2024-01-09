package com.example.site.repository;

import com.example.site.model.Administration;
import com.example.site.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AdministrationsRepository extends JpaRepository<Administration, Integer> {
    Optional<Administration> findByUser_Id(int userId);

    Administration findByUser(User user);
}

