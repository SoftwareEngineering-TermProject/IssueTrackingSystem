package com.example.IssueTrackingSystem.web.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

}
