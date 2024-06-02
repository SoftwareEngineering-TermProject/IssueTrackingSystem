package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.repository.CommentRepository;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Getter
public class ProjectQueryServiceImpl implements ProjectQueryService{

    private final ProjectRepository projectRepository;

    @Override
    public Project projectFind(ProjectRequestDTO.GetProjectRequestDTO request) {
        Optional<Project> findProject = projectRepository.findById(request.getProjectId());
        return null;
    }

    @Override
    public Project findById(Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findAllBySearch(Optional<String> optSearch) {
        // 만약 검색어가 존재한다면
        if (optSearch.isPresent()) {
            String search = optSearch.get();

            return projectRepository.findAllByTitleContainingIgnoreCaseOrderByCreatedAtDesc(search);
        }
        // 검색어 존재 X
        return projectRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Project getProject(Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        return project;
    }



}
