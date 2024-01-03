package com.example.site.controller;

import com.example.site.model.Administration;
import com.example.site.model.Department;
import com.example.site.model.Doctor;
import com.example.site.model.Patient;
import com.example.site.services.AdministrationsService;
import com.example.site.services.DepartmentsService;
import com.example.site.services.DoctorsService;
import com.example.site.services.PatientsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdministrationsController {

    private final AdministrationsService administrationsService;
    private final DoctorsService doctorsService;
    private final PatientsService patientsService;

    private final DepartmentsService departmentService;

    @Autowired
    public AdministrationsController(
            AdministrationsService administrationsService,
            DoctorsService doctorsService,
            PatientsService patientService,
            DepartmentsService departmentService) {
        this.administrationsService = administrationsService;
        this.doctorsService = doctorsService;
        this.patientsService = patientService;
        this.departmentService = departmentService;
    }
    @PostMapping("/addDoctor")
    public String addDoctor(@ModelAttribute Doctor doctor, Model model) {
        try {
            // Додаткові перевірки або логіка, якщо потрібно
            // Наприклад, перевірка, чи лікар з таким ім'ям вже існує

            // Збереження лікаря в базі даних
            doctorsService.saveDoctor(doctor);

            return "redirect:/admin/main"; // Перенаправлення після успішного додавання
        } catch (Exception e) {
            // Обробка помилок або відловлення винятків
            e.printStackTrace();
            model.addAttribute("error", "Виникла помилка при додаванні лікаря");
            return "redirect:/admin/main"; // Перенаправлення у випадку помилки
        }
    }
    @GetMapping("/deleteDoctor/{doctorId}")
    public String deleteDoctor(@PathVariable("doctorId") int doctorId, Model model) {
        try {
            // Виклик служби для видалення лікаря за його ідентифікатором
            doctorsService.deleteDoctor(doctorId);

            return "redirect:/admin/main"; // Перенаправлення після успішного видалення
        } catch (Exception e) {
            // Обробка помилок або відловлення винятків
            e.printStackTrace();
            model.addAttribute("error", "Виникла помилка при видаленні лікаря");
            return "redirect:/admin/main"; // Перенаправлення у випадку помилки
        }
    }
    @PostMapping("/deletePatient")
    public String deletePatient(@RequestParam int patientId, Model model) {
        try {
            // Виклик служби для видалення лікаря за його ідентифікатором
            patientsService.deletePatient(patientId);

            return "redirect:/admin/main"; // Перенаправлення після успішного видалення
        } catch (Exception e) {
            // Обробка помилок або відловлення винятків
            e.printStackTrace();
            model.addAttribute("error", "Виникла помилка при видаленні лікаря");
            return "redirect:/admin/main"; // Перенаправлення у випадку помилки
        }
    }
    @GetMapping("/main")
    public String getAdministrationsPage(Model model) {
        try {
            // Отримання списків лікарів, пацієнтів і департаментів зі служб (сервісів)
            List<Doctor> doctors = doctorsService.getAllDoctors();
            List<Patient> patients = patientsService.getAllPatients();
            List<Department> departments = departmentService.getAllDepartments();

            // Додавання списків до моделі
            model.addAttribute("doctors", doctors);
            model.addAttribute("patients", patients);
            model.addAttribute("departments", departments);

            // Додавання порожнього об'єкту лікаря для модального вікна додавання
            model.addAttribute("newDoctor", new Doctor());
            // Додавання порожнього об'єкту департаменту для модального вікна додавання
            model.addAttribute("newDepartment", new Department());

            // Повернення імені виду (view name)
            return "pages/adminMain";
        } catch (Exception e) {
            // Обробка помилок або вивід до консолі
            e.printStackTrace();
            model.addAttribute("error", "Виникла помилка під час отримання даних.");
            return "error"; // Повернення сторінки помилки
        }
    }


    @GetMapping("/departments")
    public String getDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("newDepartment", new Department()); // Для модального вікна додавання

        return "admin/departments"; // Відображення сторінки
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@ModelAttribute Department department, Model model) {
        // Логіка додавання відділення
        departmentService.addDepartment(department);

        return "redirect:/admin/departments"; // Перенаправлення після додавання
    }

    @PostMapping("/deleteDepartment/{departmentId}")
    public String deleteDepartment(@PathVariable("departmentId") int departmentId, Model model) {
        // Логіка видалення відділення
        departmentService.deleteDepartment(departmentId);

        return "redirect:/admin/departments"; // Перенаправлення після видалення
    }

    @GetMapping("/{id}")
    public String getAdministrationById(@PathVariable int id, Model model) {
        Optional<Administration> administration = administrationsService.getAdministrationById(id);
        model.addAttribute("administration", administration.orElse(null));
        return "administrations/administration-details";
    }

    @GetMapping("/add")
    public String showAddAdministrationForm(Model model) {
        model.addAttribute("administration", new Administration());
        return "administrations/add-administration";
    }

    @PostMapping("/add")
    public String addAdministration(@ModelAttribute Administration administration) {
        administrationsService.saveAdministration(administration);
        return "redirect:/administrations";
    }

    @GetMapping("/edit/{id}")
    public String showEditAdministrationForm(@PathVariable int id, Model model) {
        Optional<Administration> administration = administrationsService.getAdministrationById(id);
        model.addAttribute("administration", administration.orElse(null));
        return "administrations/edit-administration";
    }

    @PostMapping("/edit/{id}")
    public String editAdministration(@PathVariable int id, @ModelAttribute Administration administration) {
        administrationsService.saveAdministration(administration);
        return "redirect:/administrations";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdministration(@PathVariable int id) {
        administrationsService.deleteAdministrationById(id);
        return "redirect:/administrations";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Завершення сесії
        return "redirect:/auth/login"; // Перенаправлення на сторінку входу
    }
}

