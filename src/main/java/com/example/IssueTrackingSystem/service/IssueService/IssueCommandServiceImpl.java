package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.apiPayload.code.status.ErrorStatus;
import com.example.IssueTrackingSystem.apiPayload.exception.handler.IssueHandler;
import com.example.IssueTrackingSystem.converter.IssueConverter;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.domain.enums.Admin;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import com.example.IssueTrackingSystem.repository.ProjectUserRepository;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.IssueTrackingSystem.domain.enums.UserRole.TESTER;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IssueCommandServiceImpl implements IssueCommandService{

    private final UserRepository userRepository;
    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final ProjectUserRepository projectUserRepository;
    public Issue createIssue(Long userId, IssueRequestDTO.CreateIssueRequestDTO request){
        User getUser = userRepository.findById(userId).get();
        Project getProject = projectRepository.findById(request.getProjectId()).get();
        ProjectUser getProjectUser = projectUserRepository.findByUser_UserIdAndProject_ProjectId(userId, request.getProjectId());

        // admin과 tester만 이슈생성 가능
        if(getUser.getAdmin() == Admin.FALSE && getProjectUser.getUserRole() != TESTER){
            throw new IssueHandler(ErrorStatus.ISSUE_CREATE_UNAUTHORIZED);
        }

        Issue newIssue = IssueConverter.toIssue(request);
        newIssue.setUser(getUser);
        newIssue.setProject(getProject);

        Issue savedIssue = issueRepository.save(newIssue);

        return savedIssue;
    }

    public Issue updateIssue(Long issueId, Long userId, IssueRequestDTO.UpdateIssueDTO request){
        Issue updateIssue = issueRepository.findById(issueId).get();

        // admin과 특정 이슈 생성한 tester만 특정 이슈 업데이트 가능
        User getUser = userRepository.findById(userId).get();
        if(getUser.getAdmin() == Admin.TRUE){
            updateIssue.updateIssue(request);
            return updateIssue;
        }
        else if(userId != updateIssue.getUser().getUserId()){
            throw new IssueHandler(ErrorStatus.ISSUE_UPDATE_UNAUTHORIZED);
        }
        else {
            updateIssue.updateIssue(request);
            return updateIssue;
        }
    }

    public void deleteIssue(Long issueId, Long userId){
        Issue deleteIssue = issueRepository.findById(issueId).get();

        // admin과 특정 이슈 삭제한 tester만 특정 이슈 삭제 가능
        User getUser = userRepository.findById(userId).get();
        if(getUser.getAdmin() == Admin.TRUE){
            issueRepository.delete(deleteIssue);
            return;
        }
        else if(userId != deleteIssue.getUser().getUserId()){
            throw new IssueHandler(ErrorStatus.ISSUE_UPDATE_UNAUTHORIZED);
        }
        else {
            issueRepository.delete(deleteIssue);
            return;
        }
    }
}
