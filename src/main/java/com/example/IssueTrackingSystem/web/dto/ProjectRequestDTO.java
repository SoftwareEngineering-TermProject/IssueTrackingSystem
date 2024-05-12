package com.example.IssueTrackingSystem.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProjectRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateProjectRequestDTO {
        private String title;
        private String description;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProjectRequestDTO {
        private long projectId;
    }

}


// RequestBody는 했는데, Request Param은 어케함