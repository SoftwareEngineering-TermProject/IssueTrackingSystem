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
    //private final CommentRepository commentRepository;
    public Project projectFind(ProjectRequestDTO.GetProjectRequestDTO request) {
        //Project projectId = ProjectConverter.toProject(request);
        Optional<Project> findProject = projectRepository.findById(request.getProjectId());
        return null;
    }

    public Project findById(Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        return projectRepository.save(project);
    }

    @Override
    public Page<Project> findAllBySearch(int page, int size, Optional<String> optSearch) {
        PageRequest request = PageRequest.of(page, size);

        // if 검색어 존재
        if (optSearch.isPresent()) {
            String search = optSearch.get();
            // title, description 검색어를 포함하는 (대소문자 상관없이) 질문들을 최신순으로 페이징 조회
            return projectRepository
                    .findAllByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByCreatedAtDescProjectIdDesc(
                            search, search, request
                    );
        }
        // else 검색어 존재 X
        // 최신순으로 페이징 조회
        return projectRepository.findAllByOrderByCreatedAtDescProjectIdDesc(request);
    }

//    @Override
//    public List<Integer> findUserCountByProject(Page<Project> projects) {
//        return projects.stream().map(
//                project -> commentRepository.
//        )
//    }
}
