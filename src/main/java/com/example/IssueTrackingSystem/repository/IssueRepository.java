package com.example.IssueTrackingSystem.repository;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.enums.IssuePriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findAllByProjectAndTitleContainingIgnoreCaseOrderByCreatedAtDesc(Project project, String title);
    List<Issue> findAllByProjectOrderByCreatedAtDesc(Project project);

    Integer countByProjectAndIssuePriorityAndCreatedAtBetween(Project project, IssuePriority issuePriority, LocalDateTime start, LocalDateTime end);
    Integer countByProjectAndCreatedAtBetween(Project project, LocalDateTime start, LocalDateTime end);
}
