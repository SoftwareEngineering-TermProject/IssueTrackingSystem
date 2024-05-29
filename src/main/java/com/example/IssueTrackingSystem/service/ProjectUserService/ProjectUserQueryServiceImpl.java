package com.example.IssueTrackingSystem.service.ProjectUserService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.repository.ProjectUserRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProjectUserQueryServiceImpl implements ProjectUserQueryService {

    private final ProjectUserRepository projectUserRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public List<ProjectUser> getProjectUser(Long projectId) {
        Project getProject = projectRepository.findById(projectId).get();
        List<ProjectUser> ProjectUserList = projectUserRepository.findAllByProject(getProject);

        return ProjectUserList;
    }

    @Override
    public List<ProjectUser> getUserProject(Long userId) {
        User getUser = userRepository.findById(userId).get();
        List<ProjectUser> UserProjectList = projectUserRepository.findAllByUser(getUser);

        return UserProjectList;
    }

}
