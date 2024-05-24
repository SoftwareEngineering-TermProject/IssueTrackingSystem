package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectUser.ProjectUserRequestDTO;

public interface ProjectCommandService {
    Project createProject(Long userId, ProjectRequestDTO.CreateProjectRequestDTO request);

    Project updateProject(Long userId, Long projectId, ProjectRequestDTO.UpdateProjectDTO request);

    void deleteProject(Long userId, Long projectId);

    ProjectUser addUser(Long userId, ProjectUserRequestDTO.AddUserDTO request);
}
