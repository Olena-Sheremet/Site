package com.example.site.repository;

import com.example.site.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartmentsRepository extends JpaRepository<Department, Integer> {

}

