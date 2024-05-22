package com.example.IssueTrackingSystem.web.controller;

import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.ProjectConverter;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.service.ProjectService.ProjectCommandService;
import com.example.IssueTrackingSystem.service.ProjectService.ProjectQueryService;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

    private final ProjectCommandService projectCommandService;
    private final ProjectQueryService projectQueryService;


    // 프로젝트 생성
    @PostMapping("/")
    @Operation(summary = "프로젝트 생성", description =
            "Project를 생성합니다."
    )
    public ApiResponse<ProjectResponseDTO.CreateProjectResultDTO> projectCreate(
            @RequestParam Long userId,
            @RequestBody ProjectRequestDTO.CreateProjectRequestDTO request
            ) {
        Project newProject = projectCommandService.createProject(userId, request);
        return ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                ProjectConverter.toCreateResultDTO(newProject)
        );
    }


    // 프로젝트 수정
    @PatchMapping("/{projectId}")
    @Operation(
            summary = "프로젝트 수정 API"
            , description = "프로젝트를 수정합니다. Path variable로 projectId를 입력 받고, RequestBody에 프로젝트 제목 title과 수정할 프로젝트 description를 입력하세요"
    )
    public ApiResponse<ProjectResponseDTO.UpdateProjectResultDTO> updateProject(
            @RequestParam Long userId,
            @RequestBody ProjectRequestDTO.UpdateProjectDTO request,
            @PathVariable Long projectId
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                ProjectConverter.UpdateProjectResultDTO(
                        projectCommandService.updateProject(userId, projectId, request)
                )
        );
    }

    // 프로젝트 삭제
    @DeleteMapping("/{projectId}")
    @Operation(
            summary = "프로젝트 삭제 API"
            , description = "프로젝트를 삭제합니다. Path variable로 삭제할 projectId를 입력하세요"
    )
    public ApiResponse<?> deleteProject(
            @RequestParam Long userId,
            @PathVariable Long projectId) {

        projectCommandService.deleteProject(userId, projectId);
        return ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                null
        );
    }

    // 특정 프로젝트 조회
    @GetMapping("/{projectId}")
    @Operation(
            summary = "특정 프로젝트 조회 API"
            , description = "특정 프로젝트를 조회합니다. Path variable로 조회할 projectId를 입력하세요"
    )
    public ApiResponse<ProjectResponseDTO.ProjectDTO> findProject(
            @PathVariable Long projectId
    ) {
        Object request;
        Project findProject = projectQueryService.findById(projectId);
        return  ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                ProjectConverter.toProjectDTO(
                        findProject
                        //projectQueryService. //countExpertCountByQuestion ???
                )
        );
    }

    // 전체 프로젝트 페이징 조회 (검색 가능)
    @GetMapping
    @Operation(
            summary = "전체 프로젝트 페이징 조회 API"
            , description = "전체 프로젝트를 조회합니다. RequestParam으로 페이징 조회를 위한 page와 size를 입력하세요. 검색을 원할 경우에는 search를 입력하세요."
    )
    public ApiResponse<ProjectResponseDTO.ProjectPreviewListDTO> findProjectByPaging(
            @RequestParam @Min(0) Integer page,
            @RequestParam @Min(1) @Max(10) Integer size,
            // Search
            @RequestParam(required = false) Optional<String> search
    ) {
        Page<Project> projects = projectQueryService.findAllBySearch(page, size, search);

        //List<Integer> userCounts = projectQueryService.fin

        return ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                ProjectConverter.toProjectPreviewDTOList(
                        projects
                )
        );
    }


//    // project 조회 부분
//    @Operation(summary = "프로젝트 조회", description =
//            "프로젝트를 조회합니다."
//    )
//    @PostMapping("/{projectId}")
//    public ApiResponse<ProjectResponseDTO.GetProjectResultDTO> projectFind(
//            @RequestBody ProjectRequestDTO.GetProjectRequestDTO request
//            ) {
//        Project findProject = projectQueryService.projectFind(request);
//        return ApiResponse.onSuccess(
//                SuccessStatus.Project_OK,
//                UserConverter.toSignInResultDTO(findProject)
//        );
//    }


}
