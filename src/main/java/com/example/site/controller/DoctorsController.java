package com.example.site.controller;

import com.example.site.model.*;
import com.example.site.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorsController {

    private final DoctorsService doctorsService;
    private final PrescriptionService prescriptionService;
    private final PatientsService patientsService;
    private final DepartmentsService departmentsService;
    private final DoctorScheduleService doctorScheduleService;
    private final AppointmentScheduleService appointmentScheduleService;
    private final DoctorsService doctorService;


    @Autowired
    public DoctorsController(DoctorsService doctorsService,
                             PrescriptionService prescriptionService,
                             PatientsService patientsService,
                             AppointmentScheduleService appointmentScheduleService,
                             DoctorScheduleService doctorScheduleService,
                             DepartmentsService departmentsService,
                             DoctorsService doctorService)
    {
        this.doctorsService = doctorsService;
        this.prescriptionService = prescriptionService;
        this.patientsService = patientsService;
        this.appointmentScheduleService = appointmentScheduleService;
        this.doctorScheduleService = doctorScheduleService;
        this.departmentsService=departmentsService;
        this.doctorService = doctorService;
    }
    @ModelAttribute("departments")
    public List<Department> getAllDepartments() {

        return departmentsService.getAllDepartments();
    }
    @GetMapping
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorsService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors/all-doctors";
    }

    @GetMapping("/{id}")
    public String getDoctorById(@PathVariable int id, Model model) {
        Optional<Doctor> doctor = doctorsService.getDoctorById(id);

        if (doctor.isPresent()) {
            model.addAttribute("doctor", doctor.get());
            return "doctors/doctor-details";
        } else {
            // Обробка випадку, коли лікар не знайдено за вказаним id
            return "redirect:/doctors"; // Повертає на сторінку всіх лікарів
        }
    }

    @GetMapping("/specialty/{specialty}")
    public String getDoctorsBySpecialty(@PathVariable String specialty, Model model) {
        //List<Doctor> doctors = doctorsService.getDoctorsBySpecialty(specialty);
        //model.addAttribute("doctors", doctors);
        return "doctors/doctors-by-specialty";
    }

    // Додавання нового лікаря
    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctors/add-doctor";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctorsService.saveDoctor(doctor);
        return "redirect:/doctors/main";
    }

    // Редагування інформації про лікаря
    @GetMapping("/edit/{id}")
    public String showEditDoctorForm(@PathVariable int id, Model model) {
        Optional<Doctor> doctor = doctorsService.getDoctorById(id);

        if (doctor.isPresent()) {
            model.addAttribute("doctor", doctor.get());
            return "doctors/edit-doctor";
        } else {
            return "redirect:/doctors/main";
        }
    }

    @PostMapping("/edit/{id}")
    public String editDoctor(@PathVariable int id, @ModelAttribute Doctor doctor, Model model) {
        try {
            // Отримання існуючого лікаря за ідентифікатором
            Optional<Doctor> existingDoctor = doctorsService.getDoctorById(id);

            if (existingDoctor.isPresent()) {
                Doctor editedDoctor = existingDoctor.get();
                editedDoctor.setFirstName(doctor.getFirstName());
                editedDoctor.setLastName(doctor.getLastName());
                editedDoctor.setSpecialty(doctor.getSpecialty());
                editedDoctor.setContactNumber(doctor.getContactNumber());
                editedDoctor.setDoctorHireDate(doctor.getDoctorHireDate());
                editedDoctor.setSalary(doctor.getSalary());
                editedDoctor.setDepartment(doctor.getDepartment());

                // Встановлення ідентифікатора лікаря перед збереженням
                editedDoctor.setDoctorId(id);

                doctorsService.saveDoctor(editedDoctor);
                model.addAttribute("currentDoctor", editedDoctor);
                return "redirect:/doctors/main";
            } else {
                // Обробка випадку, коли лікаря з ідентифікатором не знайдено
                model.addAttribute("error", "Лікаря з ідентифікатором " + id + " не знайдено.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Виникла помилка під час редагування лікаря.");
            return "error";
        }
    }


    // Видалення лікаря
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable int id) {
        doctorsService.deleteDoctorById(id);
        return "redirect:/doctors/main";
    }

    @PostMapping("/addPrescription")
    public String addDepartment(@ModelAttribute Prescription prescription, Model model) {
        // Логіка додавання відділення
        prescriptionService.addPrescription(prescription);

        return "redirect:/admin/main"; // Перенаправлення після додавання
    }

    @GetMapping("/main")
    public String getDoctorsPage(Model model, HttpSession session) {
        try {
            Integer doctorId = (Integer) session.getAttribute("doctorId");

            if (doctorId != null) {
                int doctorIdValue = doctorId.intValue();

                // Отримання лікаря за ідентифікатором
                Optional<Doctor> doctor = doctorsService.getDoctorById(doctorIdValue);

                // Отримання графіка роботи для даного лікаря
                List<DoctorSchedule> doctorSchedules = doctorScheduleService.getDoctorSchedulesByDoctor(doctor.orElse(null));

                // Перевірка чи лікар знайдено
                if (doctor.isPresent()) {
                    List<AppointmentSchedule> appointmentSchedules = appointmentScheduleService.getDoctorAppointmentsAndPatientsAndPrescriptions(doctorIdValue);

                    List<Prescription> prescriptions = new ArrayList<>();
                    for (AppointmentSchedule appointment : appointmentSchedules) {
                        prescriptions.addAll(prescriptionService.getByPatient(appointment.getPatient()));
                    }

                    // Додавання атрибутів до моделі
                    model.addAttribute("currentDoctor", doctor.get());
                    model.addAttribute("appointmentSchedules", appointmentSchedules);
                    model.addAttribute("prescriptions", prescriptions);
                    model.addAttribute("doctorSchedules", doctorSchedules); // Додавання графіка роботи до моделі

                    return "pages/doctorMain";
                } else {
                    model.addAttribute("error", "Лікаря не знайдено за вказаним ID.");
                    return "error";
                }
            } else {
                model.addAttribute("error", "ID лікаря не знайдено в сесії.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Виникла помилка під час отримання даних.");
            return "error";
        }
    }

    @GetMapping("/morePatients/{doctorId}")
    public String getMorePatients(@PathVariable int doctorId, Model model) {
        try {
            Optional<Doctor> doctor = doctorsService.getDoctorById(doctorId);

            if (doctor.isPresent()) {
                List<AppointmentSchedule> appointmentSchedules = appointmentScheduleService.getDoctorAppointmentsAndPatientsAndPrescriptions(doctorId);

                model.addAttribute("currentDoctor", doctor.get());
                model.addAttribute("appointmentSchedules", appointmentSchedules);

                return "pages/doctorMain";
            } else {
                model.addAttribute("error", "Лікаря не знайдено за вказаним ID.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Виникла помилка під час отримання даних.");
            return "error";
        }
    }

    @PostMapping("/saveSchedule")// Збереження розкладу лікаря
    public String saveDoctorSchedule(@ModelAttribute DoctorSchedule editedSchedule, Model model, HttpSession session) {
        try {
            int doctorId = (Integer) session.getAttribute("doctorId");
            Doctor doctor = new Doctor();
            doctor.setDoctorId(doctorId);
            editedSchedule.setDoctor(doctor);

            // Зберегти або оновити розклад в базі даних
            doctorScheduleService.saveDoctorSchedule(editedSchedule);

            return "redirect:/doctors/main";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Виникла помилка під час збереження графіку роботи.");
            return "error";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Завершення сесії
        return "redirect:/auth/login"; // Перенаправлення на сторінку входу
    }

}
