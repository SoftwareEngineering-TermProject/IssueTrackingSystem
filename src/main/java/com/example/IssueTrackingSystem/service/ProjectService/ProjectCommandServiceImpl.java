package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.converter.ProjectConverter;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.web.dto.ProjectRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjectCommandServiceImpl implements ProjectCommandService{

    private final ProjectRepository projectRepository;
    public Project createProject(ProjectRequestDTO.CreateProjectRequestDTO request){
        Project newProject = ProjectConverter.toProject(request);
        Project savedProject = projectRepository.save(newProject);

        return savedProject;
    }
}
