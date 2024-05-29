package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.web.dto.User.UserRequestDTO;
import com.example.IssueTrackingSystem.web.dto.User.UserResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserConverter {

    public static User toUser(UserRequestDTO.CreateUserRequestDTO request){
        return User.builder()
                .name(request.getName())
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
                .userName(user.getUserName())
                .name(user.getName())
                .build();
    }

    public static UserResponseDTO.UserPreviewInIssueDTO toUserPreviewInIssueDTO(User user){
        return UserResponseDTO.UserPreviewInIssueDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }

//    public static UserResponseDTO.UserPreviewInProjectDTO toUserPreviewInProjectDTO(Project project){
//        return UserResponseDTO.UserPreviewInProjectDTO.builder()
//                .userId(project.getUser().getUserId())
//                .userName(project.getUser().getUserName())
//                .build();
//    }

    public static UserResponseDTO.UserPreviewInCommentDTO toUserPreviewInCommentDTO(User user){
        return UserResponseDTO.UserPreviewInCommentDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }


    public static UserResponseDTO.UserPreviewDTO toUserPreviewDTO(User user) {
        return UserResponseDTO.UserPreviewDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }

    public static UserResponseDTO.UserPreviewListDTO toUserPreviewListDTO(
            List<User> userList
    ) {
        List<UserResponseDTO.UserPreviewDTO> userPreviewDTOList = IntStream.range(0, userList.size())
                .mapToObj(i -> toUserPreviewDTO(userList.get(i)))
                .collect(Collectors.toList());
        return UserResponseDTO.UserPreviewListDTO.builder()
                .users(userPreviewDTOList)
                .build();
    }


}
