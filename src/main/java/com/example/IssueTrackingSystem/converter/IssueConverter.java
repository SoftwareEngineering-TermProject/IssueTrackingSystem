package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentResponseDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;
import com.sun.jdi.IntegerValue;

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
                .issuePriority(issue.getIssuePriority())
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
                .issueStatus(issue.getIssueStatus())
                .assignee(issue.getAssignee())
                .fixer(issue.getFixer())
                .issuePriority(issue.getIssuePriority())
                .createAt(issue.getCreatedAt())
                .comments(commentPreviewDTOList)
                .build();
    }

    public static IssueResponseDTO.AssigneeResultDTO toAssigneeResultDTO(Issue issue, IssueRequestDTO.AssigneeRequestDTO request){
        return IssueResponseDTO.AssigneeResultDTO.builder()
                .issueId(issue.getIssueId())
                .userName(request.getUserName())
                .build();
    }

    public static IssueResponseDTO.FixerResultDTO toFixerResultDTO(Issue issue){
        return IssueResponseDTO.FixerResultDTO.builder()
                .issueId(issue.getIssueId())
                .userName(issue.getFixer())
                .build();
    }

    public static IssueResponseDTO.IssueStatusPriorityResultDTO toIssueStatusPriorityResultDTO(Issue issue){
        return IssueResponseDTO.IssueStatusPriorityResultDTO.builder()
                .issueId(issue.getIssueId())
                .issueStatus(issue.getIssueStatus())
                .issuePriority(issue.getIssuePriority())
                .build();
    }

    public static IssueResponseDTO.GetIssueStatisticPreviewDTO toGetIssueStatisticPreviewDTO(Integer blocker, Integer critical, Integer major, Integer minor, Integer trivial, Integer total, Integer month) {
        return IssueResponseDTO.GetIssueStatisticPreviewDTO.builder()
                .month(month)
                .blocker(blocker)
                .critical(critical)
                .major(major)
                .minor(minor)
                .trivial(trivial)
                .total(total)
                .build();
    }

    public static IssueResponseDTO.GetIssueStatisticPreviewListDTO toGetIssueStatisticPreviewListDTO(
            Project project,
            int year,
            int totalIssueCountForYear,
            List<Integer> blocker,
            List<Integer> critical,
            List<Integer> major,
            List<Integer> minor,
            List<Integer> trivial,
            List<Integer> totals,
            List<Integer> month
    ) {

        List<IssueResponseDTO.GetIssueStatisticPreviewDTO> GetIssueStatisticPreviewDTOList = IntStream.range(0, month.size())
                .mapToObj(i -> toGetIssueStatisticPreviewDTO(blocker.get(i), critical.get(i), major.get(i), minor.get(i), trivial.get(i), totals.get(i), month.get(i)))
                .collect(Collectors.toList());

        return IssueResponseDTO.GetIssueStatisticPreviewListDTO.builder()
                .projectId(project.getProjectId())
                .title(project.getTitle())
                .year(year)
                .total(totalIssueCountForYear)
                .issue_count(GetIssueStatisticPreviewDTOList)
                .build();
    }
}
