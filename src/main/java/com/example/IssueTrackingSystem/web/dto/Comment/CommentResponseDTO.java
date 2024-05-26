package com.example.IssueTrackingSystem.web.dto.Comment;

import com.example.IssueTrackingSystem.domain.enums.IssueStatus;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;
import com.example.IssueTrackingSystem.web.dto.User.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateCommentResultDTO {
        Long commentId;
        LocalDateTime createAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentDTO {
        Long commentId;
        String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCommentResultDTO {
        Long commentId;
        String content;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateCommentResultDTO {
        Long commentId;
        String content;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentPreviewDTO {
        UserResponseDTO.UserPreviewInCommentDTO user;
        Long commentId;
        String content;
        LocalDateTime createdAt;
    }

}
