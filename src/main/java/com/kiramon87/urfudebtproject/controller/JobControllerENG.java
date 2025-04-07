package com.kiramon87.urfudebtproject.controller;


import com.kiramon87.urfudebtproject.entity.Job;
import com.kiramon87.urfudebtproject.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("eng")
@Validated
public class JobControllerENG {
    public final JobService jobService;

    @Autowired
    public JobControllerENG(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    @Operation()
    public String getAllJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "ENGversion/mainENG";
    }

    @GetMapping("/job/{id}")
    @Operation()
    public String getJobDetails(@PathVariable Long id, Model model) {
        Job job = jobService.getJobById(id);
        model.addAttribute("job", job);
        return "ENGversion/job-detailsENG";
    }

    @GetMapping("/apply/{id}")
    @Operation()
    public String showApplyForm(@PathVariable Long id, Model model) {
        model.addAttribute("jobId", id);
        return "ENGversion/applyENG";
    }

    @PostMapping("/job-add")
    @Operation()
    public ResponseEntity<?> addJob(@Valid @RequestBody Job job) {
        try {
            jobService.addJob(job);
            return ResponseEntity.ok("Vacancy is created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't create vacancy");
        }
    }

    @PostMapping("/delete/{id}")
    @Operation()
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        try {
            jobService.deleteJob(id);
            return ResponseEntity.ok("Vacancy was deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting the vacancy");
        }
    }

    @GetMapping("/jobs")
    @Operation()
    public String getJobs(@RequestParam(required = false) String search,
                          @RequestParam(required = false) String type,
                          Model model) {
        List<Job> jobs = jobService.findJobs(search, type);
        List<String> jobTypes = jobService.getJobTypes();

        model.addAttribute("jobs", jobs);
        model.addAttribute("jobTypes", jobTypes);
        return "ENGversion/mainENG";
    }
}
