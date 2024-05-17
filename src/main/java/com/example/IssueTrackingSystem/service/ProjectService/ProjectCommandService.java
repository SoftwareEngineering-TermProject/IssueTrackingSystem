package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;

public interface ProjectCommandService {
    Project createProject(Long userId, ProjectRequestDTO.CreateProjectRequestDTO request);

    Project updateProject(Long projectId, ProjectRequestDTO.UpdateProjectDTO request);

    void deleteProject(Long projectId);
}
