package com.example.IssueTrackingSystem.service.CommentService;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentRequestDTO;

public interface CommentCommandService {

    Comment createComment(Long userId, CommentRequestDTO.CreateCommentRequestDTO request);

    Comment updateComment(Long userId, Long commentId, CommentRequestDTO.UpdateCommentDTO request);

    void deleteComment(Long userId, Long commentId);
}
