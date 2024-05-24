package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProjectQueryService {
    Project projectFind(ProjectRequestDTO.GetProjectRequestDTO request);
    Project findById(Long projectId);

    List<Project> findAllBySearch(Optional<String> optSearch);



    // Page<Project> findAllBySearch(int page, int size, Optional<String> optSearch);

    //List<Integer> findUserCountByProject(Page<Project> projects);
}
