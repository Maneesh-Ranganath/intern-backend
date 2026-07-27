package com.polygnan.intern.repository;

import com.polygnan.intern.model.ProjectSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectSubmission, Long> {
}