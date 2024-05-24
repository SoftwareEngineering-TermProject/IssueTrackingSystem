package com.example.IssueTrackingSystem.repository;

import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectAddUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAddUserRepository extends JpaRepository<ProjectAddUser, Long> {
    ProjectAddUser findByUser_UserIdAndProject_ProjectId(Long userId, Long projectId);
    ProjectAddUser findByUser(User user);
}
