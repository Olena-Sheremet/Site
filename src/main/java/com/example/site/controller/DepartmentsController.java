package com.example.site.controller;

import com.example.site.model.Department;
import com.example.site.services.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static io.micrometer.common.util.StringUtils.isNotBlank;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {

    private final DepartmentsService departmentsService;

    @Autowired
    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentsService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments/all";
    }

    @GetMapping("/{id}")
    public String getDepartmentById(@PathVariable("id") int id, Model model) {
        Optional<Department> department = departmentsService.getDepartmentById(id);

        if (department.isPresent()) {
            model.addAttribute("department", department.get());
            return "departments/details";
        } else {
            // Обробка ситуації, коли відділення не знайдено за заданим id
            return "redirect:/departments"; // Повернення на сторінку з усіма відділеннями
        }
    }

    @GetMapping("/new")
    public String showDepartmentForm(Model model) {
        // Логіка для відображення форми для додавання нового відділення
        model.addAttribute("departmentForm", new Department());
        return "departments/new";
    }

    @PostMapping("/new")
    public String addDepartment(@ModelAttribute Department departmentForm) {
        // DepartmentForm - це клас, який ви можете створити для зберігання даних з форми

        // Перевірте, чи дані з форми не є null або порожніми перед створенням об'єкта Department
        if (departmentForm != null && isNotBlank(departmentForm.getDepartmentName())) {
            // Створення та ініціалізація об'єкта Department
            Department department = new Department();
            department.setDepartmentName(departmentForm.getDepartmentName());
            // Інші ініціалізації властивостей...

            // Виклик методу saveDepartment для збереження відділення
            departmentsService.saveDepartment(department);

            // Повернення на сторінку з усіма відділеннями після успішного збереження
            return "redirect:/departments";
        } else {
            // Обробка ситуації, коли дані з форми є недійсними або відсутніми
            return "redirect:/departments/new"; // Повертає на сторінку з формою для додавання нового відділення
        }
    }
}
