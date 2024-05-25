package com.example.IssueTrackingSystem.web.dto.ProjectUser;

import com.example.IssueTrackingSystem.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProjectUserRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddUserDTO {
        private Long projectId;
        private UserRole userRole;
    }
}
