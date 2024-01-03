package com.example.site.controller;

import com.example.site.model.AppointmentSchedule;
import com.example.site.services.AppointmentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointments")
public class AppointmentScheduleController {

    private final AppointmentScheduleService appointmentScheduleService;

    @Autowired
    public AppointmentScheduleController(AppointmentScheduleService appointmentScheduleService) {
        this.appointmentScheduleService = appointmentScheduleService;
    }

    @GetMapping("/all")
    public String getAllAppointments(Model model) {
        List<AppointmentSchedule> appointments = appointmentScheduleService.getAllAppointmentSchedules();
        model.addAttribute("appointments", appointments);
        return "appointment/all";
    }

    @GetMapping("/{id}")
    public String getAppointmentById(@PathVariable int id, Model model) {
        Optional<AppointmentSchedule> appointment = appointmentScheduleService.getAppointmentById(id);

        if (appointment.isPresent()) {
            model.addAttribute("appointment", appointment.get());
            return "appointment/details";
        } else {
            // Обробка випадку, коли запис не знайдено
            return "redirect:/appointments/all";
        }
    }

    @GetMapping("/add")
    public String showAddAppointmentForm(Model model) {
        AppointmentSchedule newAppointment = new AppointmentSchedule();
        model.addAttribute("appointment", newAppointment);
        return "appointment/add";
    }

    @PostMapping("/add")
    public String addAppointment(@ModelAttribute("appointment") AppointmentSchedule appointment) {
        appointmentScheduleService.saveAppointment(appointment);
        return "redirect:/appointments/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointmentById(@PathVariable int id) {
        appointmentScheduleService.deleteAppointmentById(id);
        return "redirect:/appointments/all";
    }

    @GetMapping("/doctor/{doctorId}")
    public String getDoctorAppointmentsAndPatientsAndPrescriptions(@PathVariable int doctorId, Model model) {
        List<AppointmentSchedule> appointments = appointmentScheduleService.getDoctorAppointmentsAndPatientsAndPrescriptions(doctorId);
        model.addAttribute("appointmentSchedules", appointments);
        return "doctorMain";
    }
}

