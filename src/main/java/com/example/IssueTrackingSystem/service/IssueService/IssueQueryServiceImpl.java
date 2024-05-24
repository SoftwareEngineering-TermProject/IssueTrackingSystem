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


    public List<Issue> findAllBySearch(Optional<String> optSearch) {
        // 만약 검색어가 존재한다면
        if (optSearch.isPresent()) {
            String search = optSearch.get();
            // title, content에 검색어를 포함하는 (대소문자관계없이) 질문들을 최신순으로 페이징 조회
            return issueRepository.findAllByTitleContainingIgnoreCaseOrderByCreatedAtDesc(search);
        }
        // 검색어가 존재하지 않는다면
        // 최신순으로 페이징 조회
        return issueRepository.findAllByOrderByCreatedAtDesc();
    }

    public Issue getIssue(Long issueId){
        return issueRepository.findById(issueId).get();
    }
}
