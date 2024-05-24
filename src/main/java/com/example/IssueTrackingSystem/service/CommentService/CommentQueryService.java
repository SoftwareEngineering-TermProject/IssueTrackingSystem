package com.example.IssueTrackingSystem.service.CommentService;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Issue;

import java.util.List;

public interface CommentQueryService {

    List<Comment> getCommentsList(Issue issue);
}
