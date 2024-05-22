package com.example.IssueTrackingSystem.web.controller;

import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.CommentConverter;
import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.service.CommentService.CommentCommandService;
//import com.example.IssueTrackingSystem.service.CommentService.CommentQueryService;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    private final CommentCommandService commentCommandService;
    //private final CommentQueryService commentQueryService;

    // 코멘트 생성
    @PostMapping("/")
    @Operation(summary = "코멘트 생성", description =
            "Comment를 생성합니다."
    )
    public ApiResponse<CommentResponseDTO.CreateCommentResultDTO> commentCreate(
            @RequestParam Long userId,
            @RequestBody CommentRequestDTO.CreateCommentRequestDTO request
            ) {
        Comment newComment = commentCommandService.createComment(userId, request);
        return ApiResponse.onSuccess(
                SuccessStatus.Comment_OK,
                CommentConverter.toCreateResultDTO(newComment)
        );
    }

    // 코멘트 수정
    @PatchMapping("/{commentId}")
    @Operation(
            summary = "코멘트 수정 API"
            , description = "코멘트를 수정합니다. Path variable로 commentId를 입력 받고, RequestBody에 수정할 코멘트 content를 입력하세요"
    )
    public ApiResponse<CommentResponseDTO.UpdateCommentResultDTO> updateComment(
            @RequestParam Long userId,
            @RequestBody CommentRequestDTO.UpdateCommentDTO request,
            @PathVariable Long commentId
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus.Comment_OK,
                CommentConverter.UpdateCommentResultDTO(
                        commentCommandService.updateComment(userId, commentId, request)
                )
        );
    }


    // 코멘트 삭제
    @DeleteMapping("/{commentId}")
    @Operation(
            summary = "코멘트 삭제 API"
            , description = "코멘트를 삭제합니다. Path variable로 삭제할 commentId를 입력하세요"
    )
    public ApiResponse<?> deleteComment(
            @RequestParam Long userId,
            @PathVariable Long commentId) {
        commentCommandService.deleteComment(userId, commentId);
        return ApiResponse.onSuccess(
                SuccessStatus.Comment_OK,
                null
        );
    }



}
