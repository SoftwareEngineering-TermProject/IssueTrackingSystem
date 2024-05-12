package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.ProjectRequestDTO;

public interface ProjectCommandService {
    Project createProject(ProjectRequestDTO.CreateProjectRequestDTO request);
}
