package com.example.IssueTrackingSystem.web.controller;

import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.ProjectConverter;
import com.example.IssueTrackingSystem.converter.UserConverter;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.service.ProjectService.ProjectCommandService;
import com.example.IssueTrackingSystem.service.ProjectService.ProjectQueryService;
import com.example.IssueTrackingSystem.web.dto.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

    private final ProjectCommandService projectCommandService;
    private final ProjectQueryService projectQueryService;

    @Operation(summary = "프로젝트 생성", description =
            "Project를 생성합니다."
    )
    @PostMapping("/")
    public ApiResponse<ProjectResponseDTO.CreateProjectResultDTO> projectCreate(   //signUp 바꿔야됨
            @RequestBody ProjectRequestDTO.CreateProjectRequestDTO request
            ) {
        Project newProject = projectCommandService.createProject(request);
        return ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                ProjectConverter.toCreateResultDTO(newProject)
        );
    }



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
