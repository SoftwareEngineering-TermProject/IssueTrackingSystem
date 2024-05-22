package com.example.IssueTrackingSystem.repository;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findAllByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String title);
    List<Issue> findAllByOrderByCreatedAtDesc();
}
