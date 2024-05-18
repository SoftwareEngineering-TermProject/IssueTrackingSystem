package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Hashtag;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectResponseDTO;
import org.springframework.data.domain.Page;

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


    public static ProjectResponseDTO.ProjectPreviewListDTO toProjectPreviewDTOList(
            Page<Project> projects
            //, List<Integer> userCountList
    ) {
        // List<ProjectResponseDTO.ProjectPreviewDTO> projectPreviewDTOList = IntStream.range(0, expertCountList.size())
        return ProjectResponseDTO.ProjectPreviewListDTO.builder()
                .listSize(projects.getContent().size())
                .totalPage(projects.getTotalPages())
                .totalElements(projects.getTotalElements())
                .isFirst(projects.isFirst())
                .isLast(projects.isLast())
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
