package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectResponseDTO;
import com.example.IssueTrackingSystem.web.dto.User.UserResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IssueConverter {
    public static Issue toIssue(IssueRequestDTO.CreateIssueRequestDTO request){
        return Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
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
}
