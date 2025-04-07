package com.kiramon87.urfudebtproject.service;


import com.kiramon87.urfudebtproject.entity.Application;
import com.kiramon87.urfudebtproject.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplication() {
        return applicationRepository.findAll();
    }

    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }
}

