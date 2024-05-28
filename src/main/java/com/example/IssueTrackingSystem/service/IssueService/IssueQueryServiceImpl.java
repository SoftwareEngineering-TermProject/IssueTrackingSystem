package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.enums.IssuePriority;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IssueQueryServiceImpl implements  IssueQueryService{

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;


    public List<Issue> findAllBySearch(Optional<String> optSearch, Long projectId) {
        Project getProject = projectRepository.findById(projectId).get();

        // 만약 검색어가 존재한다면
        if (optSearch.isPresent()) {
            String search = optSearch.get();
            // title에 검색어를 포함하는 (대소문자관계없이) 이슈들을 최신순으로 조회
            return issueRepository.findAllByProjectAndTitleContainingIgnoreCaseOrderByCreatedAtDesc(getProject, search);
        }
        // 검색어가 존재하지 않는다면
        // 최신순으로 페이징 조회
        return issueRepository.findAllByProjectOrderByCreatedAtDesc(getProject);
    }

    public Issue getIssue(Long issueId){
        return issueRepository.findById(issueId).get();
    }

    public Integer getCountOfIssueByProjectAndMonth(Long projectId, int year){
        Project getProject = projectRepository.findById(projectId).get();
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.now();
        Integer issueCount = issueRepository.countByProjectAndIssuePriorityAndCreatedAtBetween(getProject, IssuePriority.MINOR, start, end);
        System.out.println("issueCount : " + issueCount);
        return issueCount;
    }
}
