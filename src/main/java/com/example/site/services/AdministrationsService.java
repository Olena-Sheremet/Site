package com.example.site.services;

import com.example.site.model.Administration;
import com.example.site.model.Patient;
import com.example.site.model.User;
import com.example.site.repository.AdministrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministrationsService {

    private final AdministrationsRepository administrationsRepository;

    @Autowired
    public AdministrationsService(AdministrationsRepository administrationsRepository) {
        this.administrationsRepository = administrationsRepository;
    }

    public Optional<Administration> getAdministrationById(int id) {
        return administrationsRepository.findById(id);
    }


    public List<Administration> getAllAdministrations() {
        return administrationsRepository.findAll();
    }

    //public List<Administration> getAdministrationsByLastName(String lastName) {
        //return administrationsRepository.findByLastName(lastName);
   // }

    public void saveAdministration(Administration administration) {
        administrationsRepository.save(administration);
    }

    public void deleteAdministrationById(int id) {
        administrationsRepository.deleteById(id);
    }
    public Administration getAdministrationByUser(User user) {
        return administrationsRepository.findByUser(user);
    }
}

