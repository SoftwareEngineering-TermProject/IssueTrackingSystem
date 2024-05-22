package com.example.IssueTrackingSystem.repository;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByCommentOrderByCreatedAtDescCommentIdDesc(Comment comment, Pageable pageable);

}
