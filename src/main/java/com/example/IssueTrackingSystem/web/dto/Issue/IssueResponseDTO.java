package com.example.IssueTrackingSystem.web.dto.Issue;

import com.example.IssueTrackingSystem.domain.enums.IssueStatus;
import com.example.IssueTrackingSystem.web.dto.User.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class IssueResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateIssueResultDTO {
        Long issueId;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateResultDTO {
        private Long issueId;
        private String title;
        private String description;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IssuePreviewDTO {
        UserResponseDTO.UserPreviewInIssueDTO user;
        Long issueId;
        String title;
        IssueStatus issueStatus;
        String assignee;
        String fixer;
        LocalDateTime createAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IssuePreviewListDTO {
        List<IssuePreviewDTO> issues;
    }
}
