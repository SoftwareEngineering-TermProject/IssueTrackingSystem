package com.example.IssueTrackingSystem.web.dto.User;

import com.example.IssueTrackingSystem.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateUserRequestDTO {
        private String userName;
        private String password;
        private String name;
        private UserRole userRole;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignInRequestDTO {
        private String userName;
        private String password;
    }
}
