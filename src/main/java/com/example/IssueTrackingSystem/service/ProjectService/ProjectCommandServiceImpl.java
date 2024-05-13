package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.converter.ProjectConverter;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
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
    private final UserRepository userRepository;
    public Project createProject(Long userId, ProjectRequestDTO.CreateProjectRequestDTO request){
        // 프론트에서 받은 json으로 Project entity 생성
        Project newProject = ProjectConverter.toProject(request);
        User getUser = userRepository.findById(userId).get();
        newProject.setUser(getUser);

        // Project entity db에 저장
        Project savedProject = projectRepository.save(newProject);

        return savedProject;
    }
}
