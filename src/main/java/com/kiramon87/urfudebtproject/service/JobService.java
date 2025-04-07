package com.kiramon87.urfudebtproject.service;


import com.kiramon87.urfudebtproject.entity.Job;
import com.kiramon87.urfudebtproject.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public void addJob(Job job) {
        jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> findJobs(String search, String type) {
        if (search != null && !search.isEmpty() && type != null && !type.isEmpty()) {
            return jobRepository.findByTitleContainingAndType(search, type);
        } else if (search != null && !search.isEmpty()) {
            return jobRepository.findByTitleContaining(search);
        } else if (type != null && !type.isEmpty()) {
            return jobRepository.findByType(type);
        } else {
            return jobRepository.findAll();
        }
    }

    public List<String> getJobTypes() {
        return jobRepository.findDistinctTypes();
    }
}
