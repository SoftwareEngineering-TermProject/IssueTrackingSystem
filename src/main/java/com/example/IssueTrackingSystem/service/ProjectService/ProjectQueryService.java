package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.ProjectRequestDTO;

public interface ProjectQueryService {
    Project projectFind(ProjectRequestDTO.GetProjectRequestDTO request);
}
