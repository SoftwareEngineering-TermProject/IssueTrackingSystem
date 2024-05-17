package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Getter
public class ProjectQueryServiceImpl implements ProjectQueryService{

    private final ProjectRepository projectRepository;
    public Project projectFind(ProjectRequestDTO.GetProjectRequestDTO request) {
        //Project projectId = ProjectConverter.toProject(request);
        Optional<Project> findProject = projectRepository.findById(request.getProjectId());
        return null;
    }
}
