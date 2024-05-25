package com.example.IssueTrackingSystem.web.dto.Issue;

import com.example.IssueTrackingSystem.domain.enums.IssuePriority;
import com.example.IssueTrackingSystem.domain.enums.IssueStatus;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentResponseDTO;
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
        IssuePriority issuePriority;
        LocalDateTime createAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IssuePreviewListDTO {
        List<IssuePreviewDTO> issues;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetIssueResultWithCommentPreviewListDTO {
        UserResponseDTO.UserPreviewInIssueDTO user;
        Long issueId;
        String title;
        String description;
        IssueStatus issueStatus;
        String assignee;
        String fixer;
        IssuePriority issuePriority;
        LocalDateTime createAt;
        List<CommentResponseDTO.CommentPreviewDTO> comments;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AssigneeResultDTO {
        private Long issueId;
        private String userName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FixerResultDTO {
        private Long issueId;
        private String userName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IssueStatusPriorityResultDTO {
        private Long issueId;
        private IssueStatus issueStatus;
        private IssuePriority issuePriority;
    }
}
