package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.apiPayload.code.status.ErrorStatus;
import com.example.IssueTrackingSystem.apiPayload.exception.handler.IssueHandler;
import com.example.IssueTrackingSystem.converter.IssueConverter;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.IssueTrackingSystem.domain.enums.UserRole.ADMIN;
import static com.example.IssueTrackingSystem.domain.enums.UserRole.TESTER;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IssueCommandServiceImpl implements IssueCommandService{

    private final UserRepository userRepository;
    private final IssueRepository issueRepository;
    public Issue createIssue(Long userId, IssueRequestDTO.CreateIssueRequestDTO request){
        User getUser = userRepository.findById(userId).get();

        // admin과 tester만 이슈생성 가능
        if(getUser.getUserRole() != ADMIN && getUser.getUserRole() != TESTER){
            throw new IssueHandler(ErrorStatus.ISSUE_CREATE_UNAUTHORIZED);
        }

        Issue newIssue = IssueConverter.toIssue(request);
        newIssue.setUser(getUser);

        Issue savedIssue = issueRepository.save(newIssue);

        return savedIssue;
    }

    public Issue updateIssue(Long issueId, Long userId, IssueRequestDTO.UpdateIssueDTO request){
        // admin과 tester만 이슈 업데이트 가능
        User getUser = userRepository.findById(userId).get();
        if(getUser.getUserRole() != ADMIN && getUser.getUserRole() != TESTER){
            throw new IssueHandler(ErrorStatus.ISSUE_UPDATE_UNAUTHORIZED);
        }

        Issue updateIssue = issueRepository.findById(issueId).get();
        updateIssue.updateIssue(request);

        return updateIssue;
    }

    public void deleteIssue(Long issueId, Long userId){
        // admin과 tester만 이슈 삭제 가능
        User getUser = userRepository.findById(userId).get();
        if(getUser.getUserRole() != ADMIN && getUser.getUserRole() != TESTER){
            throw new IssueHandler(ErrorStatus.ISSUE_DELETE_UNAUTHORIZED);
        }

        Issue deleteIssue = issueRepository.findById(issueId).get();
        issueRepository.delete(deleteIssue);
    }
}
