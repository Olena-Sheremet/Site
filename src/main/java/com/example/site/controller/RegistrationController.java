package com.example.site.controller;

import com.example.site.model.Patient;
import com.example.site.model.User;
import com.example.site.services.PatientsService;
import com.example.site.services.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UsersService userService;
    @Autowired
    private PatientsService patientService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("patient", new Patient());
        return "pages/register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") User user, @ModelAttribute("patient") Patient patient, HttpSession session) {
        try {
            // Перевірка наявності імені користувача
            if (userService.userNameExists(user.getUserName())) {
                return "pages/register";
            }

            // Збереження користувача
            userService.saveUser(user);

            // Прив'язка користувача до пацієнта після збереження
            patient.setUser(user);
            patientService.savePatient(patient);

            // Встановлення об'єкта користувача у сесію
            session.setAttribute("user", user);

            return "redirect:/auth/login";
        } catch (Exception e) {
            // Обробка помилки (ви можете вивести її в консоль або журнал логів)
            e.printStackTrace();
            // Повернення на сторінку реєстрації з помилкою
            return "pages/register";
        }
    }
}