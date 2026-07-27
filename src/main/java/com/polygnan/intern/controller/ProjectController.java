package com.polygnan.intern.controller;

import com.polygnan.intern.model.ProjectSubmission;
import com.polygnan.intern.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") // Allows React frontend requests
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // Base Health Endpoint
    @GetMapping("/health")
    public String healthCheck() {
        return "Clean Intern Backend is running successfully!";
    }

    // Student Submission Form Endpoint
    @PostMapping
    public ProjectSubmission submitProject(@RequestBody ProjectSubmission submission) {
        if (submission.getStatus() == null || submission.getStatus().trim().isEmpty()) {
            submission.setStatus("PENDING");
        }
        return projectRepository.save(submission);
    }

    // Host Mode Access Control Endpoint (Fetch all submissions)
    @GetMapping
    public List<ProjectSubmission> getAllProjects() {
        return projectRepository.findAll();
    }

    // Host Mode Endpoint (Update status) - Accepts JSON Body
    @PutMapping("/{id}/status")
    public ProjectSubmission updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = (body != null && body.containsKey("status")) ? body.get("status") : "PENDING";
        ProjectSubmission submission = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found with id: " + id));
        submission.setStatus(status);
        return projectRepository.save(submission);
    }
}