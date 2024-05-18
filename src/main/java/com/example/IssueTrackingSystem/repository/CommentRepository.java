package com.example.IssueTrackingSystem.repository;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepository {

    Page<Comment> findByProjectOrderByCreatedAtDescCommentIdDesc(Project project, Pageable pageable);
}
