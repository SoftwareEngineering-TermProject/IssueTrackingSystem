package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectAddUser;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectAddUser.ProjectAddUserRequestDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectAddUser.ProjectAddUserResponseDTO;

public class ProjectAddUserConverter {
    public static ProjectAddUser toProjectAddUser(ProjectAddUserRequestDTO.AddUserDTO request){
        return ProjectAddUser.builder()
                .userRole(request.getUserRole())
                .build();
    }

    public static ProjectAddUserResponseDTO.AddUserResultDTO toAddUserResultDTO(ProjectAddUser projectAddUser){
        return ProjectAddUserResponseDTO.AddUserResultDTO.builder()
                .userId(projectAddUser.getUser().getUserId())
                .userName(projectAddUser.getUser().getUserName())
                .userRole(projectAddUser.getUserRole())
                .build();
    }
}
