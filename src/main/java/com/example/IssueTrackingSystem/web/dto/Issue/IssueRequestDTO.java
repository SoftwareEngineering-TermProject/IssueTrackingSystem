package com.example.IssueTrackingSystem.web.dto.Issue;

import com.example.IssueTrackingSystem.domain.enums.IssuePriority;
import com.example.IssueTrackingSystem.domain.enums.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class IssueRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateIssueRequestDTO {
        private Long projectId;
        private String title;
        private String description;
        private IssuePriority issuePriority;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateIssueDTO {
        private String title;
        private String description;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AssigneeRequestDTO {
        private String userName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IssueStatusPriorityRequestDTO {
        private IssueStatus issueStatus;
        private IssuePriority issuePriority;
    }
}
