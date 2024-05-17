package com.example.IssueTrackingSystem.web.dto.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProjectResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateProjectResultDTO {
        Long projectId;
        String title;
        String description;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectDTO {
        Long projectId;
        String title;
        String description;
        // 이슈 상태?
        // 코멘트?
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetProjectResultDTO {
        Long projectId;
        String title;
        String description;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateProjectResultDTO {
        Long projectId;
        String title;
        String description;

    }

}
