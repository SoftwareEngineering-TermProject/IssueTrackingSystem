package com.example.IssueTrackingSystem.web.dto.ProjectAddUser;

import com.example.IssueTrackingSystem.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProjectAddUserResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddUserResultDTO {
        Long userId;
        String userName;
        UserRole userRole;
    }
}
