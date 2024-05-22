package com.example.IssueTrackingSystem.web.controller;

import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.IssueConverter;
import com.example.IssueTrackingSystem.converter.ProjectConverter;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.service.IssueService.IssueCommandService;
import com.example.IssueTrackingSystem.service.IssueService.IssueQueryService;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/issues")
@Slf4j
public class IssueController {

    private final IssueCommandService issueCommandService;
    private final IssueQueryService issueQueryService;

    // 이슈 생성
    @PostMapping("/")
    @Operation(summary = "이슈 생성 API", description =
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

    // 이슈 수정
    @PatchMapping("/{issueId}")
    @Operation(
            summary = "이슈 수정 API"
            , description = "이슈를 수정합니다. Path variable로 issueId를 입력 받고, RequestBody에 수정할 이슈 title과 description를 입력하세요"
    )
    public ApiResponse<IssueResponseDTO.UpdateResultDTO> updateIssue(
            @PathVariable Long issueId,
            @RequestParam Long userId,
            @RequestBody IssueRequestDTO.UpdateIssueDTO request
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toUpdateResultDTO(
                        issueCommandService.updateIssue(issueId, userId, request)
                )
        );
    }

    // 이슈 삭제
    @DeleteMapping("/{issueId}")
    @Operation(
            summary = "이슈 삭제 API"
            , description = "이슈를 삭제합니다. Path variable로 삭제할 userId를 입력하세요"
    )
    public ApiResponse<?> deleteIssue(
            @PathVariable Long issueId,
            @RequestParam Long userId
    ) {

        issueCommandService.deleteIssue(issueId, userId);
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                null
        );
    }

    // 이슈 리스트 출력
    @DeleteMapping("/{issueId}")
    @Operation(
            summary = "전체 이슈 조회 API"
            , description = "전체 이슈를 조회합니다."
    )
    public ApiResponse<?> getIssues(
            @RequestParam Optional<String> search
    ) {

        List<Issue> Issues = issueQueryService.findAllBySearch(search);
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                null
        );
    }
}
