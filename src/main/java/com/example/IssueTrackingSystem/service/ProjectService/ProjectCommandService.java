package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectUser.ProjectUserRequestDTO;

public interface ProjectCommandService {

    // 프로젝트 생성
    Project createProject(Long userId, ProjectRequestDTO.CreateProjectRequestDTO request);

    // 프로젝트 수정
    Project updateProject(Long userId, Long projectId, ProjectRequestDTO.UpdateProjectDTO request);

    // 프로젝트 삭제
    void deleteProject(Long userId, Long projectId);

    // 프로젝트 참여자 추가
    ProjectUser addUser(Long userId, ProjectUserRequestDTO.AddUserDTO request);

    // 프로젝트 참여 사용자 삭제
    void deleteUserInProject(Long userId, Long projectId, Long adminId);

}
