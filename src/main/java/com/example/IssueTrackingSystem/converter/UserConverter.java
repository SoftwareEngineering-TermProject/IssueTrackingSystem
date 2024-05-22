package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.web.dto.User.UserRequestDTO;
import com.example.IssueTrackingSystem.web.dto.User.UserResponseDTO;

public class UserConverter {

    public static User toUser(UserRequestDTO.CreateUserRequestDTO request){
        return User.builder()
                .name(request.getName())
                .userRole(request.getUserRole())
                .userName(request.getUserName())
                .password(request.getPassword())
                .build();
    }

    public static UserResponseDTO.CreateUserResultDTO toCreateResultDTO(User user){
        return UserResponseDTO.CreateUserResultDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .build();
    }

    public static User toUser(UserRequestDTO.SignInRequestDTO request){
        return User.builder()
                .userName(request.getUserName())
                .password(request.getPassword())
                .build();
    }

    public static UserResponseDTO.SignInResultDTO toSignInResultDTO(User user){
        return UserResponseDTO.SignInResultDTO.builder()
                .userId(user.getUserId())
                .build();
    }

    public static UserResponseDTO.UserPreviewInIssueDTO toUserPreviewInIssueDTO(User user){
        return UserResponseDTO.UserPreviewInIssueDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }
}
