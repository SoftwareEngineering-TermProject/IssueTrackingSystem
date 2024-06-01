package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.enums.IssuePriority;

import java.util.List;
import java.util.Optional;

public interface IssueQueryService {
    List<Issue> findAllBySearch(Optional<String> optSearch, Long projectId);
    Issue getIssue(Long issueId);

    List<Integer> getCountOfIssuePriorityIssueByProjectAndMonth(Long projectId, int year, IssuePriority issuePriority);
    List<Integer> getCountOfTotalIssue(Long projectId, int year);
    Integer getTotalIssueCountForYear(int year, Long projectId);

    List<Issue> findIssueByAssignee(Long userId, Long projectId);

    List<Integer> getMonth();
}
