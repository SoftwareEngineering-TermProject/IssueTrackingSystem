package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;

public interface ProjectQueryService {
    Project projectFind(ProjectRequestDTO.GetProjectRequestDTO request);
    Project findById(Long projectId);
}
