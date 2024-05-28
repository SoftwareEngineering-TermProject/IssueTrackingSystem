package com.example.IssueTrackingSystem.repository;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {
    ProjectUser findByUser_UserIdAndProject_ProjectId(Long userId, Long projectId);

    List<ProjectUser> findAllByProject(Project project);
    List<ProjectUser> findAllByUser(User user);

    //ProjectUser findByProjectId(@Param("projectId") Long projectId);
    //ProjectUser findByProject(Long projectId);
    ProjectUser findByUser(User user);

    ProjectUser deleteByUserAndProject(User user, Project project);
}
