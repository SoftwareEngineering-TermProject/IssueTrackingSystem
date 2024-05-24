package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentResponseDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IssueConverter {
    public static Issue toIssue(IssueRequestDTO.CreateIssueRequestDTO request){
        return Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .issuePriority(request.getIssuePriority())
                .build();
    }

    public static IssueResponseDTO.CreateIssueResultDTO toCreateIssueResultDTO(Issue issue){
        return IssueResponseDTO.CreateIssueResultDTO.builder()
                .issueId(issue.getIssueId())
                .createdAt(issue.getCreatedAt())
                .build();
    }

    public static IssueResponseDTO.UpdateResultDTO toUpdateResultDTO(Issue issue){
        return IssueResponseDTO.UpdateResultDTO.builder()
                .issueId(issue.getIssueId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .build();
    }

    public static IssueResponseDTO.IssuePreviewDTO toIssuePreviewDTO(Issue issue) {
        return IssueResponseDTO.IssuePreviewDTO.builder()
                .user(UserConverter.toUserPreviewInIssueDTO(issue.getUser()))
                .issueId(issue.getIssueId())
                .title(issue.getTitle())
                .issueStatus(issue.getIssueStatus())
                .assignee(issue.getAssignee())
                .fixer(issue.getFixer())
                .createAt(issue.getCreatedAt())
                .build();
    }

    public static IssueResponseDTO.IssuePreviewListDTO toIssuePreviewListDTO(
            List<Issue> issueList
    ) {

        List<IssueResponseDTO.IssuePreviewDTO> issuePreviewDTOList = IntStream.range(0, issueList.size())
                .mapToObj(i -> toIssuePreviewDTO(issueList.get(i)))
                .collect(Collectors.toList());

        return IssueResponseDTO.IssuePreviewListDTO.builder()
                .issues(issuePreviewDTOList)
                .build();
    }

    public static IssueResponseDTO.GetIssueResultWithCommentPreviewListDTO toGetIssueResultWithCommentPreviewListDTO(
            Issue issue,
            List<Comment> commentList
    ) {

        List<CommentResponseDTO.CommentPreviewDTO> commentPreviewDTOList = IntStream.range(0, commentList.size())
                .mapToObj(i -> CommentConverter.toCommentPreviewDTO(commentList.get(i)))
                .collect(Collectors.toList());

        return IssueResponseDTO.GetIssueResultWithCommentPreviewListDTO.builder()
                .user(UserConverter.toUserPreviewInIssueDTO(issue.getUser()))
                .issueId(issue.getIssueId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .assignee(issue.getAssignee())
                .fixer(issue.getFixer())
                .createAt(issue.getCreatedAt())
                .comments(commentPreviewDTOList)
                .build();
    }
}
