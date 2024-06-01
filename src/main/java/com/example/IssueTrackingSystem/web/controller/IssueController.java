package com.example.IssueTrackingSystem.web.controller;

import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.IssueConverter;
import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.enums.IssuePriority;
import com.example.IssueTrackingSystem.service.CommentService.CommentQueryService;
import com.example.IssueTrackingSystem.service.IssueService.IssueCommandService;
import com.example.IssueTrackingSystem.service.IssueService.IssueQueryService;
import com.example.IssueTrackingSystem.service.ProjectService.ProjectQueryService;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueResponseDTO;
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
    private final ProjectQueryService projectQueryService;

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
            "Issue에 담당자를 지정합니다.\n userId에 Assignee를 지정하는 유저(PL)의 userId를 입력하세요\n userName에 지정당하는 유저(DEV)의 userName을 입력하세요. PL과 admin만 Assignee지정이 가능합니다."
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
            "Issue에 수정자를 지정합니다.\n userId에 이슈 수정자로 지정될 assignee의 userId를 입력하세요. Assignee와 admin만 지정 가능합니다."
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
    @PatchMapping("/status/priority{issueId}")
    @Operation(
            summary = "이슈 상태, 우선순위 수정 API"
            , description = "이슈 상태를 수정합니다."
    )
    public ApiResponse<IssueResponseDTO.IssueStatusPriorityResultDTO> updateIssueStatusPriority(
            @PathVariable Long issueId,
            @RequestBody IssueRequestDTO.IssueStatusPriorityRequestDTO request
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toIssueStatusPriorityResultDTO(
                        issueCommandService.updateIssueStatusPriority(issueId, request)
                )
        );
    }

    // 이슈 담당자 삭제
    @DeleteMapping("/issues/assignee/{issueId}")
    @Operation(
            summary = "이슈 담당자 삭제 API\n Assignee삭제를 시도하려는 유저를 userId에 입력하세요. PL과 admin만 삭제 가능합니다."
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
            summary = "이슈 수정자 삭제 API\n userId에 Fixer를 삭제를 시도하려는 userId를 입력하세요. Fixer본인과 admin만 삭제 가능합니다."
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

    // 이슈 통계 조회
    @GetMapping("/statistic/{projectId}")
    @Operation(
            summary = "이슈 통계 조회 API"
    )
    public ApiResponse<?> getIssueCountByYear(
            @PathVariable Long projectId,
            @RequestParam int year
    ) {
        Project getProject = projectQueryService.getProject(projectId);
        int totalIssueCountForYear = issueQueryService.getTotalIssueCountForYear(year, projectId);
        List<Integer> issueCountByBlocker = issueQueryService.getCountOfIssuePriorityIssueByProjectAndMonth(projectId, year, IssuePriority.BLOCKER);
        List<Integer> issueCountByCritical = issueQueryService.getCountOfIssuePriorityIssueByProjectAndMonth(projectId, year, IssuePriority.CRITICAL);
        List<Integer> issueCountByMajor = issueQueryService.getCountOfIssuePriorityIssueByProjectAndMonth(projectId, year, IssuePriority.MAJOR);
        List<Integer> issueCountByMinor = issueQueryService.getCountOfIssuePriorityIssueByProjectAndMonth(projectId, year, IssuePriority.MINOR);
        List<Integer> issueCountByTrivial = issueQueryService.getCountOfIssuePriorityIssueByProjectAndMonth(projectId, year, IssuePriority.CRITICAL);
        List<Integer> issueTotalCount = issueQueryService.getCountOfTotalIssue(projectId, year);
        List<Integer> month = issueQueryService.getMonth();

        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toGetIssueStatisticPreviewListDTO(getProject, year, totalIssueCountForYear, issueCountByBlocker, issueCountByCritical, issueCountByMajor, issueCountByMinor, issueCountByTrivial, issueTotalCount, month)
        );
    }

    // 유저 본인이 assigned되어 있는 이슈 리스트 조회
    @GetMapping("/list/assignee/{projectId}")
    @Operation(
            summary = "유저 본인이 assigned되어 있는 이슈 리스트 조회 API"
            , description = "유저 본인이 assigned되어 있는 이슈 리스트를 조회합니다."
    )
    public ApiResponse<IssueResponseDTO.IssuePreviewListDTO> getIssueAssigneeList(
            @PathVariable Long projectId,
            @RequestParam Long userId
    ) {

        List<Issue> Issues = issueQueryService.findIssueByAssignee(userId, projectId);
        return ApiResponse.onSuccess(
                SuccessStatus.Issue_OK,
                IssueConverter.toIssuePreviewListDTO(Issues)
        );
    }
}
