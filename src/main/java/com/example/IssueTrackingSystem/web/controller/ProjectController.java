package com.example.IssueTrackingSystem.web.controller;

import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.ProjectUserConverter;
import com.example.IssueTrackingSystem.converter.ProjectConverter;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.service.ProjectService.ProjectCommandService;
import com.example.IssueTrackingSystem.service.ProjectService.ProjectQueryService;
import com.example.IssueTrackingSystem.service.UserService.UserQueryService;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectResponseDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectUser.ProjectUserRequestDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectUser.ProjectUserResponseDTO;
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
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

    private final ProjectCommandService projectCommandService;
    private final ProjectQueryService projectQueryService;
    private final UserQueryService userQueryService;


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


    // 프로젝트 리스트 검색
    @GetMapping
    @Operation(
            summary = "프로젝트 리스트 검색 API"
            , description = "프로젝트 전체 리스트를 조회하거나 특정 프로젝트를 검색할 수 있습니다."
    )
    public ApiResponse<?> getProjects(
            @RequestParam Optional<String> search
    ) {
        List<Project> Projects = projectQueryService.findAllBySearch(search);
        return ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                ProjectConverter.toProjectPreviewListDTO(Projects)
        );
    }

    // admin이 프로젝트에 계정 추가
    @PostMapping("/add/{userId}")
    @Operation(summary = "프로젝트에 계정 추가", description =
            "Project에 계정을 추가합니다."
    )
    public ApiResponse<ProjectUserResponseDTO.ProjectUserResultDTO> addUser(
            @PathVariable Long userId,
            @RequestBody ProjectUserRequestDTO.AddUserDTO request
    ) {
        ProjectUser newProjectUser = projectCommandService.addUser(userId, request);
        return ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                ProjectUserConverter.toProjectUserResultDTO(newProjectUser)  //newProjectUser
        );
    }

//    // admin이 프로젝트 내에서 계정 삭제
//    @DeleteMapping("/delete/{adminId}")
//    @Operation(summary = "프로젝트 내의 계정 삭제", description =
//            "Project에 참여중인 계정을 삭제합니다. adminId는 삭제를 진행하는 유저가 admin인지 확인하고, userId는 삭제 대상 유저를 나타냄"
//    )
//    public ApiResponse<?> deleteUser(
//            @PathVariable Long adminId,  // 삭제를 진행하는 유저가 admin인지 확인
//            @RequestParam Long projectId,
//            @RequestBody ProjectRequestDTO.deleteUserInProjectDTO request
//    ) {
//        projectCommandService.deleteUserInProject(request, projectId, adminId);
//        return ApiResponse.onSuccess(
//                SuccessStatus.Project_OK,
//                null
//        );
//    }

    @DeleteMapping("/delete")
    @Operation(summary = "프로젝트 내의 계정 삭제", description =
            "Project에 참여중인 계정을 삭제합니다. adminId는 삭제를 진행하는 유저가 admin인지 확인하고, userId는 삭제 대상 유저를 나타냄"
    )
    public ApiResponse<?> deleteUser(
            @RequestParam Long adminId,  // 삭제를 진행하는 유저가 admin인지 확인
            @RequestParam Long projectId,
            @RequestParam Long userId
    ) {
        projectCommandService.deleteUserInProject(userId, projectId, adminId);
        return ApiResponse.onSuccess(
                SuccessStatus.Project_OK,
                null
        );
    }

}
