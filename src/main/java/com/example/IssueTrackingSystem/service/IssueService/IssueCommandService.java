package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;

public interface IssueCommandService {
    Issue createIssue(Long userId, IssueRequestDTO.CreateIssueRequestDTO request);

    Issue updateIssue(Long issueId, Long userId, IssueRequestDTO.UpdateIssueDTO request);

    void deleteIssue(Long issueId, Long userId);
}
