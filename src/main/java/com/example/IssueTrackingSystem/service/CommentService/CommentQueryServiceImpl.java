package com.example.IssueTrackingSystem.service.CommentService;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.repository.CommentRepository;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentQueryServiceImpl implements CommentQueryService{

    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsList(Issue issue){
        return commentRepository.findAllByIssue(issue);
    }
}
