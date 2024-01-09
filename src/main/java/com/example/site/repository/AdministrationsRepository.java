package com.example.site.repository;

import com.example.site.model.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdministrationsRepository extends JpaRepository<Administration, Integer> {

}

