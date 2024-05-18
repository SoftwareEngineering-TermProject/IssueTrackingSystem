package com.example.IssueTrackingSystem.service.IssueService;

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

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IssueCommandServiceImpl implements IssueCommandService{

    private final UserRepository userRepository;
    private final IssueRepository issueRepository;
    public Issue createIssue(Long userId, IssueRequestDTO.CreateIssueRequestDTO request){
        User getUser = userRepository.findById(userId).get();
        Issue newIssue = IssueConverter.toIssue(request);
        newIssue.setUser(getUser);

        Issue savedIssue = issueRepository.save(newIssue);

        return savedIssue;
    }
}
