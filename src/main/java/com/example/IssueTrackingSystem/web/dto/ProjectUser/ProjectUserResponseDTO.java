package com.example.IssueTrackingSystem.web.dto.ProjectUser;

import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProjectUserResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectUserResultDTO {
        Long userId;
        String userName;
        UserRole userRole;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectUserResultListDTO {
        List<ProjectUserResultDTO> users;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserProjectResultDTO {
        Long projectId;
        String title;
        UserRole userRole;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserProjectResultListDTO {
        List<UserProjectResultDTO> projects;
    }


}
