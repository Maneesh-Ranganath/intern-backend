package com.polygnan.intern.controller;

import com.polygnan.intern.model.ProjectSubmission;
import com.polygnan.intern.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") // Allows React frontend requests
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // Student Submission Form Endpoint
    @PostMapping
    public ProjectSubmission submitProject(@RequestBody ProjectSubmission submission) {
        if (submission.getStatus() == null) {
            submission.setStatus("PENDING");
        }
        return projectRepository.save(submission);
    }

    // Host Mode Access Control Endpoint (Fetch all submissions)
    @GetMapping
    public List<ProjectSubmission> getAllProjects() {
        return projectRepository.findAll();
    }

    // Host Mode Endpoint (Update status)
    @PutMapping("/{id}/status")
    public ProjectSubmission updateStatus(@PathVariable Long id, @RequestParam String status) {
        ProjectSubmission submission = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found with id: " + id));
        submission.setStatus(status);
        return projectRepository.save(submission);
    }
}