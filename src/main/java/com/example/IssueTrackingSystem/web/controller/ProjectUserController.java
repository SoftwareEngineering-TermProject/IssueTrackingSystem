package com.example.IssueTrackingSystem.web.controller;


import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.ProjectUserConverter;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.service.ProjectUserService.ProjectUserQueryService;
import com.example.IssueTrackingSystem.service.ProjectService.ProjectQueryService;
import com.example.IssueTrackingSystem.service.UserService.UserQueryService;
import com.example.IssueTrackingSystem.web.dto.ProjectUser.ProjectUserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/projects")
@Slf4j
public class ProjectUserController {

    private final ProjectQueryService projectQueryService;
    private final UserQueryService userQueryService;
    private final ProjectUserQueryService projectUserQueryService;

    // 프로젝트 참여 유저 조회 (프로젝트에 포함된 유저 조회)
    @GetMapping("/participants/{projectId}")
    @Operation(
            summary = "프로젝트 참여 유저 검색 API"
            , description = "프로젝트에 참여한 유저를 조회할 수 있습니다."
    )
    public ApiResponse<ProjectUserResponseDTO.ProjectUserResultListDTO> findProjectUser(
            @PathVariable Long projectId
    ) {
        List<ProjectUser> projectUserList = projectUserQueryService.getProjectUser(projectId);
        return ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                ProjectUserConverter.toProjectUserResultListDTO(projectUserList)
        );
    }

}
