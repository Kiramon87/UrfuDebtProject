package com.kiramon87.urfudebtproject.controller;

import com.kiramon87.urfudebtproject.entity.Application;
import com.kiramon87.urfudebtproject.entity.Job;
import com.kiramon87.urfudebtproject.service.ApplicationService;
import com.kiramon87.urfudebtproject.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("eng/apply")
@Validated
public class ApplicationControllerENG {
    public final ApplicationService applicationService;
    public final JobService jobService;

    @Autowired
    public ApplicationControllerENG(ApplicationService applicationService, JobService jobService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
    }

    @PostMapping("/{jobId}")
    @Operation()
    public String submitApplication(@PathVariable Long jobId, @RequestParam String name, @RequestParam String email,
                                    @RequestParam String message) {

        Job job = jobService.getJobById(jobId);

        Application application = new Application();
        application.setJob(job);
        application.setStudent(name);
        application.setEmail(email);
        application.setMessage(message);

        applicationService.saveApplication(application);

        return "redirect:/eng";
    }

    @GetMapping("/applications")
    @Operation()
    public String getMyApplications(Model model) {
        model.addAttribute("applications", applicationService.getAllApplication());
        return "ENGversion/my-applicationsENG";
    }


    @PostMapping("/delete/{id}")
    @Operation()
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        try {
            applicationService.deleteById(id);
            return ResponseEntity.ok("Application was deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting the Application");
        }
    }

}
