package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IssueQueryService {
    List<Issue> findAllBySearch(Optional<String> optSearch, Long projectId);
    Issue getIssue(Long issueId);

    List<Integer> getCountOfBlockerIssueByProjectAndMonth(Long projectId, int year);
    List<Integer> getCountOfCriticalIssueByProjectAndMonth(Long projectId, int year);
    List<Integer> getCountOfMajorIssueByProjectAndMonth(Long projectId, int year);
    List<Integer> getCountOfMinorIssueByProjectAndMonth(Long projectId, int year);
    List<Integer> getCountOfTrivialIssueByProjectAndMonth(Long projectId, int year);
    List<Integer> getCountOfTotalIssue(Long projectId, int year);
    Integer getTotalIssueCountForYear(int year, Long projectId);

    List<Integer> getMonth();
}
