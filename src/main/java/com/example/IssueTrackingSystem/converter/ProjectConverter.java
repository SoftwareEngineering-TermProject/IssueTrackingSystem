package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectResponseDTO;

public class ProjectConverter {

    public static Project toProject(ProjectRequestDTO.CreateProjectRequestDTO request){
        return Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }

    public static ProjectResponseDTO.CreateProjectResultDTO toCreateResultDTO(Project project){
        return ProjectResponseDTO.CreateProjectResultDTO.builder()
                .projectId(project.getProjectId())
                .build();
    }
}
