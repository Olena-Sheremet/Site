package com.example.site.services;

import com.example.site.repository.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.site.model.Department;
@Service
public class DepartmentsService {

    private final DepartmentsRepository departmentsRepository;

    @Autowired
    public DepartmentsService(DepartmentsRepository departmentsRepository) {
        this.departmentsRepository = departmentsRepository;
    }
    public Optional<Department> getDepartmentById(int id) {
        return departmentsRepository.findById(id);
    }
    public List<Department> getAllDepartments() {
        return departmentsRepository.findAll();
    }

    public void addDepartment(Department department) {
        departmentsRepository.save(department);
    }

    public void deleteDepartment(int departmentId) {
        departmentsRepository.deleteById(departmentId);
    }

    public Department saveDepartment(Department department) {
        return departmentsRepository.save(department);
    }

    public void deleteDepartmentById(int id) {
        departmentsRepository.deleteById(id);
    }
}
