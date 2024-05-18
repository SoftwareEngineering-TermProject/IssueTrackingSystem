package com.example.IssueTrackingSystem.web.controller;

import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.IssueConverter;
import com.example.IssueTrackingSystem.converter.ProjectConverter;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.service.IssueService.IssueCommandService;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/issues")
@Slf4j
public class IssueController {

    private final IssueCommandService issueCommandService;

    // 프로젝트 생성
    @PostMapping("/")
    @Operation(summary = "이슈 생성", description =
            "Issue를 생성합니다."
    )
    public ApiResponse<IssueResponseDTO.CreateIssueResultDTO> createIssue(
            @RequestParam Long userId,
            @RequestBody IssueRequestDTO.CreateIssueRequestDTO request
    ) {
        Issue newIssue = issueCommandService.createIssue(userId, request);
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toCreateIssueResultDTO(newIssue)
        );
    }
}
