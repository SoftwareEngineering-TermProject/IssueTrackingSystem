package com.example.IssueTrackingSystem.web.dto.ProjectAddUser;

import com.example.IssueTrackingSystem.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProjectAddUserRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddUserDTO {
        private Long projectId;
        private UserRole userRole;
    }
}
