package com.example.IssueTrackingSystem.web.dto.Project;

import com.example.IssueTrackingSystem.domain.enums.UserRole;
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

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateProjectDTO {
        private String title;
        private String description;
    }

//    @Getter
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class deleteProjectDTO {
//        private String title;
//        private String description;
//    }

}

