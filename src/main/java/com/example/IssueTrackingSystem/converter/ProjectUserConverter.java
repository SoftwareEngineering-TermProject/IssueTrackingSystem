package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.web.dto.ProjectUser.ProjectUserRequestDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectUser.ProjectUserResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProjectUserConverter {

    public static ProjectUserResponseDTO.ProjectUserResultDTO toProjectUserResultDTO(ProjectUser projectUser){
        return ProjectUserResponseDTO.ProjectUserResultDTO.builder()
                .projectId(projectUser.getProject().getProjectId())
                .title(projectUser.getProject().getTitle())
                .userId(projectUser.getUser().getUserId())
                .userName(projectUser.getUser().getUserName())
                .userRole(projectUser.getUserRole())
                .build();
    }

    public static ProjectUserResponseDTO.ProjectUserResultListDTO toProjectUserResultListDTO(List<ProjectUser> projectUserList){
        List<ProjectUserResponseDTO.ProjectUserResultDTO> projectUserResultDTOList = IntStream.range(0, projectUserList.size())
                .mapToObj(i -> toProjectUserResultDTO(projectUserList.get(i)))
                .collect(Collectors.toList());
        return ProjectUserResponseDTO.ProjectUserResultListDTO.builder()
                .users(projectUserResultDTOList)
                .build();
    }


    public static ProjectUser toProjectUser(ProjectUserRequestDTO.AddUserDTO request){
        return ProjectUser.builder()
                .userRole(request.getUserRole())
                .build();
    }

}
