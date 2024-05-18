package com.example.IssueTrackingSystem.web.dto.Issue;

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
        private String title;
        private String description;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateIssueDTO {
        private String title;
        private String description;
    }
}
