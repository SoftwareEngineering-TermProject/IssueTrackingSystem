package com.example.IssueTrackingSystem.web.controller;

import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.IssueConverter;
import com.example.IssueTrackingSystem.converter.ProjectConverter;
import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.enums.IssuePriority;
import com.example.IssueTrackingSystem.domain.enums.IssueStatus;
import com.example.IssueTrackingSystem.service.CommentService.CommentQueryService;
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
    private final CommentQueryService commentQueryService;

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

    // 이슈 리스트 검색
    @GetMapping("/list/{projectId}")
    @Operation(
            summary = "전체 이슈 조회 API"
            , description = "전체 이슈를 조회합니다."
    )
    public ApiResponse<IssueResponseDTO.IssuePreviewListDTO> getIssueList(
            @PathVariable Long projectId,
            @RequestParam Optional<String> search
    ) {

        List<Issue> Issues = issueQueryService.findAllBySearch(search, projectId);
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toIssuePreviewListDTO(Issues)
        );
    }

    // 이슈 리스트 검색
    @GetMapping("/{issueId}")
    @Operation(
            summary = "특정 이슈 조회 API"
            , description = "특정 이슈를 조회합니다."
    )
    public ApiResponse<IssueResponseDTO.GetIssueResultWithCommentPreviewListDTO> getIssue(
            @PathVariable Long issueId
    ) {
        Issue getIssue = issueQueryService.getIssue(issueId);
        List<Comment> commentsList = commentQueryService.getCommentsList(getIssue);

        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toGetIssueResultWithCommentPreviewListDTO(getIssue, commentsList)
        );
    }

    // 이슈 담당자 지정
    @PostMapping("/assignee/{issueId}")
    @Operation(summary = "이슈 담당자 지정 API", description =
            "Issue에 담당자를 지정합니다."
    )
    public ApiResponse<IssueResponseDTO.AssigneeResultDTO> addAssignee(
            @PathVariable Long issueId,
            @RequestParam Long userId,
            @RequestBody IssueRequestDTO.AssigneeRequestDTO request
    ) {
        Issue newIssue = issueCommandService.addAssignee(userId, issueId, request);
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toAssigneeResultDTO(newIssue, request)
        );
    }

    // 이슈 수정자 지정
    @PostMapping("/fixer/{issueId}")
    @Operation(summary = "이슈 수정자 지정 API", description =
            "Issue에 수정자를 지정합니다."
    )
    public ApiResponse<IssueResponseDTO.FixerResultDTO> addFixer(
            @PathVariable Long issueId,
            @RequestParam Long userId
    ) {
        Issue newIssue = issueCommandService.addFixer(userId, issueId);
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toFixerResultDTO(newIssue)
        );
    }

    // 이슈 상태 수정
    @PatchMapping("/status/{issueId}")
    @Operation(
            summary = "이슈 상태 수정 API"
            , description = "이슈 상태를 수정합니다."
    )
    public ApiResponse<IssueResponseDTO.IssueStatusResultDTO> updateIssue(
            @PathVariable Long issueId,
            @RequestParam IssueStatus issueStatus
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toIssueStatusResultDTO(
                        issueCommandService.updateIssueStatus(issueId, issueStatus)
                )
        );
    }

    // 이슈 우선순위 수정
    @PatchMapping("/priority/{issueId}")
    @Operation(
            summary = "이슈 우선순위 수정 API"
            , description = "이슈 우선순위를 수정합니다."
    )
    public ApiResponse<IssueResponseDTO.IssuePriorityResultDTO> updateIssue(
            @PathVariable Long issueId,
            @RequestParam IssuePriority issuePriority
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toIssuePriorityResultDTO(
                        issueCommandService.updateIssuePriority(issueId, issuePriority)
                )
        );
    }

    // 이슈 담당자 삭제
    @DeleteMapping("/issues/assignee/{issueId}")
    @Operation(
            summary = "이슈 담당자 삭제 API"
    )
    public ApiResponse<?> deleteIssueAssignee(
            @PathVariable Long issueId,
            @RequestParam Long userId
    ) {

        issueCommandService.deleteIssueAssignee(issueId, userId);
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                null
        );
    }

    // 이슈 수정자 삭제
    @DeleteMapping("/issues/fixer/{issueId}")
    @Operation(
            summary = "이슈 수정자 삭제 API"
    )
    public ApiResponse<?> deleteIssueFixer(
            @PathVariable Long issueId,
            @RequestParam Long userId
    ) {

        issueCommandService.deleteIssueFixer(issueId, userId);
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                null
        );
    }
}
