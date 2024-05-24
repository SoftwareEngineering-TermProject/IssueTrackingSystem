package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Hashtag;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentResponseDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommentConverter {

    public static Comment toComment(CommentRequestDTO.CreateCommentRequestDTO request){
        return Comment.builder()
                .comment(request.getContent())
                .build();
    }

    public static CommentResponseDTO.CreateCommentResultDTO toCreateResultDTO(Comment comment) {
        return CommentResponseDTO.CreateCommentResultDTO.builder()
                .commentId(comment.getCommentId())
                .createAt(comment.getCreatedAt())
                .build();
    }

    public static CommentResponseDTO.CommentDTO toCommentDTO(Comment comment) {
        List<Hashtag> hashtagList = CommentHashtagConverter.toHashtagList(
                comment.getCommentHashTagList()
        );

        return CommentResponseDTO.CommentDTO.builder()
                .commentId(comment.getCommentId())
                .content(comment.getComment())
                .build();
    }

    public static CommentResponseDTO.UpdateCommentResultDTO UpdateCommentResultDTO(Comment comment) {
        return CommentResponseDTO.UpdateCommentResultDTO.builder()
                .commentId(comment.getCommentId())
                .content(comment.getComment())
                .build();
    }

    public static CommentResponseDTO.CommentPreviewDTO toCommentPreviewDTO(Comment comment) {
        return CommentResponseDTO.CommentPreviewDTO.builder()
                .user(UserConverter.toUserPreviewInCommentDTO(comment.getUser()))
                .commentId(comment.getCommentId())
                .content(comment.getComment())
                .build();
    }
}
