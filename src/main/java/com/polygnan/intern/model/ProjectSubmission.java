package com.polygnan.intern.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String studentEmail;
    private String projectTitle;

    @Column(columnDefinition = "TEXT")
    private String projectDescription;

    private String githubUrl;
    private String liveDemoUrl;
    private String status; // e.g., "PENDING", "APPROVED", "REJECTED"
}