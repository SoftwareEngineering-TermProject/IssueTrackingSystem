package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.apiPayload.code.status.ErrorStatus;
import com.example.IssueTrackingSystem.apiPayload.exception.handler.IssueHandler;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.domain.enums.IssuePriority;
import com.example.IssueTrackingSystem.domain.enums.UserRole;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.repository.ProjectUserRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IssueQueryServiceImpl implements  IssueQueryService{

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectUserRepository projectUserRepository;
    private List<Integer> issueCount_total = new ArrayList<>();

    @Override
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

    @Override
    public List<Integer> getCountOfBlockerIssueByProjectAndMonth(Long projectId, int year){
        List<Integer> issueCount = new ArrayList<Integer>();
        Project getProject = projectRepository.findById(projectId).get();
        for(int i = 1; i <= 12; i++){
            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                issueCount.add(getIssueCountForDay31(year, i, IssuePriority.BLOCKER, projectId));
            }
            else if(i == 2){
                issueCount.add(getIssueCountForDay28(year, i, IssuePriority.BLOCKER, projectId));
            }
            else{
                issueCount.add(getIssueCountForDay30(year, i, IssuePriority.BLOCKER, projectId));
            }
        }
        return issueCount;
    }

    @Override
    public List<Integer> getCountOfCriticalIssueByProjectAndMonth(Long projectId, int year){
        List<Integer> issueCount = new ArrayList<Integer>();
        Project getProject = projectRepository.findById(projectId).get();
        for(int i = 1; i <= 12; i++){
            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                issueCount.add(getIssueCountForDay31(year, i, IssuePriority.CRITICAL, projectId));
            }
            else if(i == 2){
                issueCount.add(getIssueCountForDay28(year, i, IssuePriority.CRITICAL, projectId));
            }
            else{
                issueCount.add(getIssueCountForDay30(year, i, IssuePriority.CRITICAL, projectId));
            }
        }
        return issueCount;
    }

    @Override
    public List<Integer> getCountOfMajorIssueByProjectAndMonth(Long projectId, int year){
        List<Integer> issueCount = new ArrayList<Integer>();
        Project getProject = projectRepository.findById(projectId).get();
        for(int i = 1; i <= 12; i++){
            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                issueCount.add(getIssueCountForDay31(year, i, IssuePriority.MAJOR, projectId));
            }
            else if(i == 2){
                issueCount.add(getIssueCountForDay28(year, i, IssuePriority.MAJOR, projectId));
            }
            else{
                issueCount.add(getIssueCountForDay30(year, i, IssuePriority.MAJOR, projectId));
            }
        }
        return issueCount;
    }

    @Override
    public List<Integer> getCountOfMinorIssueByProjectAndMonth(Long projectId, int year){
        List<Integer> issueCount = new ArrayList<Integer>();
        Project getProject = projectRepository.findById(projectId).get();
        for(int i = 1; i <= 12; i++){
            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                issueCount.add(getIssueCountForDay31(year, i, IssuePriority.MINOR, projectId));
            }
            else if(i == 2){
                issueCount.add(getIssueCountForDay28(year, i, IssuePriority.MINOR, projectId));
            }
            else{
                issueCount.add(getIssueCountForDay30(year, i, IssuePriority.MINOR, projectId));
            }
        }
        return issueCount;
    }

    @Override
    public List<Integer> getCountOfTrivialIssueByProjectAndMonth(Long projectId, int year){
        List<Integer> issueCount = new ArrayList<Integer>();
        Project getProject = projectRepository.findById(projectId).get();
        for(int i = 1; i <= 12; i++){
            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                issueCount.add(getIssueCountForDay31(year, i, IssuePriority.TRIVIAL, projectId));
            }
            else if(i == 2){
                issueCount.add(getIssueCountForDay28(year, i, IssuePriority.TRIVIAL, projectId));
            }
            else{
                issueCount.add(getIssueCountForDay30(year, i, IssuePriority.TRIVIAL, projectId));
            }
        }
        return issueCount;
    }

    @Override
    public List<Integer> getCountOfTotalIssue(Long projectId, int year){
        List<Integer> issueCount = new ArrayList<Integer>();
        Project getProject = projectRepository.findById(projectId).get();
        for(int i = 1; i <= 12; i++){
            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                LocalDateTime start = LocalDateTime.of(LocalDate.of(year, i, 1), LocalTime.of(0, 0, 0));
                LocalDateTime end = LocalDateTime.of(LocalDate.of(year, i, 31), LocalTime.of(0, 0, 0));
                issueCount.add(issueRepository.countByProjectAndCreatedAtBetween(getProject, start, end));
            }
            else if(i == 2){
                LocalDateTime start = LocalDateTime.of(LocalDate.of(year, i, 1), LocalTime.of(0, 0, 0));
                LocalDateTime end = LocalDateTime.of(LocalDate.of(year, i, 28), LocalTime.of(0, 0, 0));
                issueCount.add(issueRepository.countByProjectAndCreatedAtBetween(getProject, start, end));
            }
            else{
                LocalDateTime start = LocalDateTime.of(LocalDate.of(year, i, 1), LocalTime.of(0, 0, 0));
                LocalDateTime end = LocalDateTime.of(LocalDate.of(year, i, 30), LocalTime.of(0, 0, 0));
                issueCount.add(issueRepository.countByProjectAndCreatedAtBetween(getProject, start, end));
            }
        }
        return issueCount;
    }

    public Integer getIssueCountForDay31(int year, int i, IssuePriority issuePriority, Long projectId){
        Project getProject = projectRepository.findById(projectId).get();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(year, i, 1), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(year, i, 31), LocalTime.of(0, 0, 0));
        return issueRepository.countByProjectAndIssuePriorityAndCreatedAtBetween(getProject, issuePriority, start, end);
    }

    public Integer getIssueCountForDay30(int year, int i, IssuePriority issuePriority, Long projectId){
        Project getProject = projectRepository.findById(projectId).get();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(year, i, 1), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(year, i, 30), LocalTime.of(0, 0, 0));
        return issueRepository.countByProjectAndIssuePriorityAndCreatedAtBetween(getProject, issuePriority, start, end);
    }

    public Integer getIssueCountForDay28(int year, int i, IssuePriority issuePriority, Long projectId){
        Project getProject = projectRepository.findById(projectId).get();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(year, i, 1), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(year, i, 28), LocalTime.of(0, 0, 0));
        return issueRepository.countByProjectAndIssuePriorityAndCreatedAtBetween(getProject, issuePriority, start, end);
    }

    @Override
    public Integer getTotalIssueCountForYear(int year, Long projectId){
        Project getProject = projectRepository.findById(projectId).get();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(year, 1, 1), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(year, 12, 31), LocalTime.of(0, 0, 0));
        return issueRepository.countByProjectAndCreatedAtBetween(getProject, start, end);
    }

    @Override
    public List<Integer> getMonth(){
        List <Integer> month = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        return month;
    }

    @Override
    public List<Issue> findIssueByAssignee(Long userId, Long projectId) {
        User getUser = userRepository.findById(userId).get();
        Project getProject = projectRepository.findById(projectId).get();
        ProjectUser getProjectUser = projectUserRepository.findByUser_UserIdAndProject_ProjectId(userId, projectId);

        if(getProjectUser.getUserRole() != UserRole.DEV){
            throw new IssueHandler(ErrorStatus.USER_DEV_UNAUTHORIZED);
        }

        return issueRepository.findAllByProjectAndAssigneeOrderByCreatedAtDesc(getProject, getUser.getUserName());
    }
}
