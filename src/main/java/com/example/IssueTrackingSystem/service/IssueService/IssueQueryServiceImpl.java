package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IssueQueryServiceImpl implements  IssueQueryService{
    private final IssueRepository issueRepository;
    public List<Issue> findAllBySearch(Optional<String> search) {
        return issueRepository.findAllByTitleContainingIgnoreCaseByCreatedAtDesc();
    }

}
