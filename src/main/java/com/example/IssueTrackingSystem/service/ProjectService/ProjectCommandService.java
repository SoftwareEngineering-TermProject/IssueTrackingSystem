package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectAddUser;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectAddUser.ProjectAddUserRequestDTO;

public interface ProjectCommandService {
    Project createProject(Long userId, ProjectRequestDTO.CreateProjectRequestDTO request);

    Project updateProject(Long userId, Long projectId, ProjectRequestDTO.UpdateProjectDTO request);

    void deleteProject(Long userId, Long projectId);

    ProjectAddUser addUser(Long userId, ProjectAddUserRequestDTO.AddUserDTO request);
}
