package com.example.IssueTrackingSystem.service.ProjectUserService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.repository.ProjectUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProjectUserQueryServiceImpl implements ProjectUserQueryService {

    private final ProjectUserRepository projectUserRepository;
    private final ProjectRepository projectRepository;

    public List<ProjectUser> getProjectUser(Long projectId) {
        Project getProject = projectRepository.findById(projectId).get();
        List<ProjectUser> ProjectUserList = projectUserRepository.findByProject(getProject);

        return ProjectUserList;
    }

}
