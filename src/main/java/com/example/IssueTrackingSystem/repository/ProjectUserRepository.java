package com.example.IssueTrackingSystem.repository;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {
    ProjectUser findByUser_UserIdAndProject_ProjectId(Long userId, Long projectId);

    List<ProjectUser> findByProject(Project project);
}
