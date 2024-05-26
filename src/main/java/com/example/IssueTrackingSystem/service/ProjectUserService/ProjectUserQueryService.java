package com.example.IssueTrackingSystem.service.ProjectUserService;

import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;

import java.util.List;
import java.util.Optional;

public interface ProjectUserQueryService {

    //ProjectUser getProjectUser(Long projectId);
    List<ProjectUser> getProjectUser(Long projectId);
    List<ProjectUser> getUserProject(Long userId);
}
