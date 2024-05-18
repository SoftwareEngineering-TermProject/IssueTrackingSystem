package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectResponseDTO;

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
}
