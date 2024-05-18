package com.example.IssueTrackingSystem.web.dto.Issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class IssueResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateIssueResultDTO {
        Long issueId;
        LocalDateTime createdAt;
    }
}
