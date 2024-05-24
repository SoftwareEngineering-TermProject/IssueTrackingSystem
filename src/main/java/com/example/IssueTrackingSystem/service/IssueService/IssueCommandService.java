package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.enums.IssuePriority;
import com.example.IssueTrackingSystem.domain.enums.IssueStatus;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;

public interface IssueCommandService {
    Issue createIssue(Long userId, IssueRequestDTO.CreateIssueRequestDTO request);

    Issue updateIssue(Long issueId, Long userId, IssueRequestDTO.UpdateIssueDTO request);

    void deleteIssue(Long issueId, Long userId);

    Issue addAssignee(Long userId, Long issueId, IssueRequestDTO.AssigneeRequestDTO reqeust);

    Issue addFixer(Long userId, Long issueId);

    Issue updateIssueStatus(Long issueId, IssueStatus issueStatus);
    Issue updateIssuePriority(Long issueId, IssuePriority issuePriority);
}
