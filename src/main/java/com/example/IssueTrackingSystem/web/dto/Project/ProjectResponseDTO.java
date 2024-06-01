package com.example.IssueTrackingSystem.web.dto.Project;

import com.example.IssueTrackingSystem.domain.enums.UserRole;
import com.example.IssueTrackingSystem.web.dto.Hashtag.HashtagResponseDTO;
import com.example.IssueTrackingSystem.web.dto.User.UserResponseDTO;
import lombok.*;

import java.util.List;

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
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectPreviewDTO {  // 특정 프로젝트 조회
        Long projectId;
        String title;
        String description;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectPreviewListDTO {  // 전체 프로젝트 리스트 조회
        List<ProjectPreviewDTO> projects;
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

    @Data
    @Builder
    public static class UserDTO {
        Long userId;
        String userName;
    }

}
