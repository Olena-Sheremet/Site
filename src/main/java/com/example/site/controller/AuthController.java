package com.example.site.controller;

import com.example.site.model.*;
import com.example.site.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Date;

@Controller
@RequestMapping("/auth")
@SessionAttributes("user")
public class AuthController {

    @Autowired
    private UsersService userService;
    @Autowired
    private PatientsService patientService;
    @Autowired
    private DoctorsService doctorService;
    @Autowired
    private AdministrationsService administrationService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "pages/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String inputUsername, @RequestParam String password, Model model, HttpSession session) {
        try {
            User user = userService.authenticate(inputUsername, password);

            if (user != null) {
                // Встановити об'єкт користувача у сесію лише, якщо його там ще немає
                if (session.getAttribute("user") == null) {
                    session.setAttribute("user", user);
                }

                // Перевірка типу користувача та перенаправлення відповідно
                switch (user.getUserType()) {

                    case "patient":
                        return "redirect:/patients/main";
                    case "administration":
                        return "redirect:/admin/main";
                    case "doctor":
                        // Отримайте doctorId під час аутентифікації лікаря
                        int doctorId = doctorService.getDoctorByUserId(user.getUserId()).getDoctorId();

                        // Встановіть атрибут doctorId у сесії
                        session.setAttribute("doctorId", doctorId);

                        return "redirect:/doctors/main";
                    default:
                        model.addAttribute("error", "Невідомий тип користувача");
                        return "pages/login";
                }
            } else {
                model.addAttribute("error", "Невірне ім'я користувача або пароль");
                return "pages/login";
            }
        } catch (Exception e) {
            // Обробка помилок або відловлення винятків
            e.printStackTrace();
            model.addAttribute("error", "Виникла помилка при авторизації: " + e.getMessage());
            return "pages/login";
        }
    }



    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userModel", new User());
        model.addAttribute("patientModel", new Patient());
        model.addAttribute("doctorModel", new Doctor());
        model.addAttribute("administratorModel", new Administration());
        return "pages/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userModel") User user,
                               @ModelAttribute("patientModel") Patient patient,
                               @ModelAttribute("doctorModel") Doctor doctor,
                               @ModelAttribute("administratorModel") Administration administrator,
                               @RequestParam("userType") String userType,
                               HttpSession session) {

        try {
            // Перевірка наявності імені користувача
            if (userService.userNameExists(user.getUserName())) {
                return "pages/register";
            }

            // Збереження користувача
            userService.saveUser(user);

            // Відповідно до вибору типу користувача збережіть відповідний об'єкт (Пацієнт, Лікар, Адміністратор)
            switch (userType) {
                case "patient":
                    // Прив'язка користувача до пацієнта після збереження
                    patient.setUser(user);
                    patientService.savePatient(patient);
                    break;
                case "doctor":
                    // Прив'язка користувача до лікаря після збереження
                    doctor.setUser(user);
                    doctorService.saveDoctor(doctor);
                    break;
                case "administrator":
                    // Прив'язка користувача до адміністратора після збереження
                    administrator.setUser(user);
                    administrationService.saveAdministration(administrator);
                    break;
                default:
                    // Невідомий тип користувача
                    return "pages/register";
            }


            // Встановлення об'єкта користувача у сесію
            session.setAttribute("user", user);

            return "redirect:/auth/login";
        }catch (DataIntegrityViolationException e) {
            // Обробка помилки збереження
            e.printStackTrace();
            // Виведення інформації про помилку (може бути передана на сторінку)
            return "pages/register";
        } catch (Exception e) {
            // Обробка інших помилок
            e.printStackTrace();
            // Виведення інформації про помилку (може бути передана на сторінку)
            return "pages/register";
        }
    }

}