package com.example.site.controller;

import com.example.site.model.Prescription;
import com.example.site.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public String getAllPrescriptions(Model model) {
        model.addAttribute("prescriptions", prescriptionService.getAllPrescriptions());
        return "prescriptions/list";
    }

    @GetMapping("/{prescriptionId}")
    public String getPrescriptionById(@PathVariable int prescriptionId, Model model) {
        model.addAttribute("prescription", prescriptionService.getPrescriptionById(prescriptionId));
        return "prescriptions/details";
    }

    @GetMapping("/new")
    public String showPrescriptionForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        return "prescriptions/new";
    }

    @PostMapping("/new")
    public String addPrescription(@ModelAttribute Prescription prescription) {
        prescriptionService.savePrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/{prescriptionId}/edit")
    public String showEditForm(@PathVariable int prescriptionId, Model model) {
        model.addAttribute("prescription", prescriptionService.getPrescriptionById(prescriptionId));
        return "prescriptions/edit";
    }

    @PostMapping("/{prescriptionId}/edit")
    public String editPrescription(@PathVariable int prescriptionId, @ModelAttribute Prescription prescription) {
        prescription.setPrescriptionId(prescriptionId);
        prescriptionService.savePrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/{prescriptionId}/delete")
    public String deletePrescription(@PathVariable int prescriptionId) {
        prescriptionService.deletePrescriptionById(prescriptionId);
        return "redirect:/prescriptions";
    }
}

