package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Hashtag;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectResponseDTO;

import java.util.List;

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
                .title(project.getTitle())
                .description(project.getDescription())
                .build();
    }


    public static ProjectResponseDTO.ProjectDTO toProjectDTO(Project project){
        List<Hashtag> hashtagList = ProjectHashtagConverter.toHashtagList(
                project.getProjectHashTagList()
        );

        return ProjectResponseDTO.ProjectDTO.builder()
                .projectId(project.getProjectId())
                .title(project.getTitle())
                .description(project.getDescription())
                .build();
    }

    public static ProjectResponseDTO.UpdateProjectResultDTO UpdateProjectResultDTO(Project project) {
        return ProjectResponseDTO.UpdateProjectResultDTO.builder()
                .projectId(project.getProjectId())
                .title(project.getTitle())
                .description(project.getDescription())
                .build();
    }
}
