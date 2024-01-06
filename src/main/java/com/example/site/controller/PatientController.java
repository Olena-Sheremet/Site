package com.example.site.controller;

import com.example.site.model.*;
import com.example.site.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientsService patientService;
    private final AppointmentScheduleService appointmentScheduleService;
    private final DoctorsService doctorsService;
    private final DoctorScheduleService doctorScheduleService;
    private final PrescriptionService prescriptionService;
    @Autowired
    public PatientController(PatientsService patientService, AppointmentScheduleService appointmentScheduleService, DoctorsService doctorsService, DoctorScheduleService doctorScheduleService, PrescriptionService prescriptionService) {
        this.patientService = patientService;
        this.appointmentScheduleService = appointmentScheduleService;
        this.doctorsService = doctorsService;
        this.doctorScheduleService = doctorScheduleService;
        this.prescriptionService = prescriptionService;
    }


    @GetMapping("/main")
    public String showMainPage(HttpSession session, Model model) {

        // Отримуємо користувача з сесії
        User user = (User) session.getAttribute("user");
        // Отримуємо пацієнта за користувачем
        Patient patient = patientService.getPatientByUser(user);
        // Отримуємо список лікарів
        List<Doctor> doctors = doctorsService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        // Отримуємо заплановані прийоми для пацієнта
        List<AppointmentSchedule> scheduledAppointments = appointmentScheduleService.getScheduledAppointmentsForPatient(patient);
        model.addAttribute("scheduledAppointments", scheduledAppointments);
        // Отримуємо рецепти для пацієнта
        List<Prescription> prescriptions = prescriptionService.getByPatient(patient);
        model.addAttribute("prescriptions", prescriptions);


        // Додаємо об'єкт пацієнта до моделі
        model.addAttribute("patient", patient);
        return "pages/patientMain";
    }


    @PostMapping("/updatePersonalInfo")
    public String updatePersonalInfo(Patient updatedPatient, HttpSession session) {
        // Отримуємо користувача з сесії
        User user = (User) session.getAttribute("user");

        // Отримуємо пацієнта за користувачем
        Patient existingPatient = patientService.getPatientByUser(user);

        // Перевірка, чи існує пацієнт (необхідний пацієнт у базі даних)
        if (existingPatient != null) {
            // Оновлюємо дані пацієнта
            existingPatient.setFirstName(updatedPatient.getFirstName());
            existingPatient.setLastName(updatedPatient.getLastName());
            existingPatient.setPatientDateOfBirth(updatedPatient.getPatientDateOfBirth());
            existingPatient.setGender(updatedPatient.getGender());
            existingPatient.setContactNumber(updatedPatient.getContactNumber());
            existingPatient.setEmail(updatedPatient.getEmail());
            existingPatient.setContactAddress(updatedPatient.getContactAddress());

            // Зберігаємо оновлені дані пацієнта
            patientService.savePatient(existingPatient);

            return "redirect:/patients/main";
        } else {
            // Якщо немає пацієнта, можна виконати необхідні дії (можливо, перенаправити на сторінку створення пацієнта)
            return "redirect:/createPatient";  // Припустима сторінка для створення пацієнта
        }
    }
    @GetMapping("/appointments")
    public String showScheduledAppointments(Model model, HttpSession session) {
        // Отримуємо користувача з сесії
        User user = (User) session.getAttribute("user");

        // Отримуємо пацієнта за користувачем
        Patient patient = patientService.getPatientByUser(user);

        // Отримуємо заплановані прийоми для пацієнта
        List<AppointmentSchedule> scheduledAppointments = appointmentScheduleService.getScheduledAppointmentsForPatient(patient);

        // Додаємо список запланованих прийомів до моделі
        model.addAttribute("scheduledAppointments", scheduledAppointments);

        // Отримуємо дані про лікарів для відображення їх в інформації про прийоми
        List<Doctor> doctors = doctorsService.getAllDoctors();
        model.addAttribute("doctors", doctors);

        return "pages/patientMain";
    }

    @GetMapping
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients/list";
    }



    @GetMapping("/{patientId}")
    public String getPatientById(@PathVariable int patientId, Model model) {
        model.addAttribute("patient", patientService.getPatientById(patientId).orElse(null));
        return "patients/details";
    }

    @GetMapping("/new")
    public String showPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/new";
    }

    @PostMapping("/new")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/{patientId}/edit")
    public String showEditForm(@PathVariable int patientId, Model model) {
        model.addAttribute("patient", patientService.getPatientById(patientId).orElse(null));
        return "patients/edit";
    }

    @PostMapping("/{patientId}/edit")
    public String editPatient(@PathVariable int patientId, @ModelAttribute Patient patient) {
        patient.setPatientId(patientId);
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/{patientId}/delete")
    public String deletePatient(@PathVariable int patientId) {
        patientService.deletePatientById(patientId);
        return "redirect:/patients";
    }
    @GetMapping("/{patientId}/updatePersonalInfo")
    public String showUpdatePersonalInfoForm(@PathVariable int patientId, Model model) {
        model.addAttribute("patient", patientService.getPatientById(patientId).orElse(null));
        return "patients/updatePersonalInfo";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Завершення сесії
        return "redirect:/auth/login"; // Перенаправлення на сторінку входу
    }
    @PostMapping("/bookAppointment")
    public String bookAppointment(@RequestParam int doctorId, @RequestParam String appointmentDateTime, HttpSession session) {
        // Отримуємо користувача з сесії
        User user = (User) session.getAttribute("user");

        // Отримуємо пацієнта за користувачем
        Patient patient = patientService.getPatientByUser(user);

        // Отримуємо об'єкт лікаря за його ідентифікатором
        Doctor doctor = doctorsService.getDoctorById(doctorId).orElseThrow(() -> new RuntimeException("Лікар не знайдений"));

        // Створення об'єкту запису на прийом
        AppointmentSchedule appointmentSchedule = new AppointmentSchedule();
        appointmentSchedule.setPatient(patient);
        appointmentSchedule.setDoctor(doctor);

        // Парсинг та встановлення дати та часу прийому
        LocalDateTime appointmentDateTimeParsed = LocalDateTime.parse(appointmentDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        appointmentSchedule.setAppointmentDate(java.sql.Date.valueOf(appointmentDateTimeParsed.toLocalDate()));
        appointmentSchedule.setAppointmentTime(java.sql.Time.valueOf(appointmentDateTimeParsed.toLocalTime()));
        // Встановлення значення для statusSchedule
        appointmentSchedule.setStatusSchedule("Scheduled");
        // Збереження запису на прийом
        appointmentScheduleService.saveAppointment(appointmentSchedule);

        return "redirect:/patients/main";
    }

}