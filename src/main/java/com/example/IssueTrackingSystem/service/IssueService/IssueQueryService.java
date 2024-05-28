package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;

import java.util.List;
import java.util.Optional;

public interface IssueQueryService {
    List<Issue> findAllBySearch(Optional<String> optSearch, Long projectId);
    Issue getIssue(Long issueId);

    Integer getCountOfIssueByProjectAndMonth(Long projectId, int year);
}
