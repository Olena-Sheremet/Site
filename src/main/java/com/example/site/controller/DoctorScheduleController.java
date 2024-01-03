package com.example.site.controller;

import com.example.site.model.DoctorSchedule;
import com.example.site.services.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
@Controller
@RequestMapping("/doctors-schedule")
public class DoctorScheduleController {

    private final DoctorScheduleService doctorScheduleService;

    @Autowired
    public DoctorScheduleController(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }

    @GetMapping
    public String getAllDoctorSchedules(Model model) {
        List<DoctorSchedule> doctorSchedules = doctorScheduleService.getAllDoctorSchedules();
        model.addAttribute("doctorSchedules", doctorSchedules);
        return "redirect:/doctorMain";
    }

    @GetMapping("/{scheduleId}")
    public String getDoctorScheduleById(@PathVariable int scheduleId, Model model) {
        model.addAttribute("doctorSchedule", doctorScheduleService.getDoctorScheduleById(scheduleId).orElse(null));
        return "doctors-schedule/details";
    }

    @PostMapping("/save")
    public String saveDoctorSchedule(@ModelAttribute DoctorSchedule doctorSchedule) {
        try {
            // Логіка для збереження графіка роботи лікаря
            doctorScheduleService.saveDoctorSchedule(doctorSchedule);

            // Повернення на сторінку зі списком графіка роботи
            return "redirect:/doctorMain";
        } catch (Exception e) {
            e.printStackTrace();
            // Обробка помилки
            return "error";
        }
    }

    @GetMapping("/delete/{scheduleId}")
    public String deleteDoctorScheduleById(@PathVariable int scheduleId) {
        doctorScheduleService.deleteDoctorScheduleById(scheduleId);
        return "redirect:/doctors-schedule";
    }
}

