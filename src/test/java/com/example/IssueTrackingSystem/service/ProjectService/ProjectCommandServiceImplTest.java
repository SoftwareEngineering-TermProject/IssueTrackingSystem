package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.apiPayload.exception.handler.ProjectHandler;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.domain.enums.Admin;
import com.example.IssueTrackingSystem.domain.enums.UserRole;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.repository.ProjectUserRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import com.example.IssueTrackingSystem.web.dto.ProjectUser.ProjectUserRequestDTO;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectCommandServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectUserRepository projectUserRepository;

    @InjectMocks
    private ProjectCommandServiceImpl projectCommandService;


    // 프로젝트 생성 TEST
    @Test
    void createProject() {
        // when
        Long userId = 1L;
        String title = "testTitle";
        String description = "testDescription";

        Long projectId = 1L;

        Project expectProject = Project.builder()
                .projectId(projectId)
                .title(title)
                .description(description)
                .build();

        User testUser = User.builder()
                .userId(userId)
                .build();

        expectProject.setUser(testUser);

        // userRepository.findById
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // projectRepository.save
        when(projectRepository.save(any(Project.class))).thenReturn(expectProject);

        // given
        ProjectRequestDTO.CreateProjectRequestDTO request = ProjectRequestDTO.CreateProjectRequestDTO.builder()
                .title(title)
                .description(description)
                .build();

        Project project = projectCommandService.createProject(userId, request);

        //then
        // 검증
        assertEquals(project.getProjectId(), projectId);
        assertEquals(project.getTitle(), title);
        assertEquals(project.getDescription(), description);

        // 연관관계 검증
        assertEquals(project.getUser().getUserId(), userId);
    }


    // 프로젝트 수정 TEST
    @Test
    void updateProject() {
        // given
        Long projectId = 1L;
        Long userId = 1L;
        String updateTitle = "TestUpdateTitle";
        String updateDescription = "TestUpdateDescription";

        ProjectRequestDTO.UpdateProjectDTO request = ProjectRequestDTO.UpdateProjectDTO.builder()
                .title(updateTitle)
                .description(updateDescription)
                .build();

        User testUser = User.builder()
                .userId(userId)
                .admin(Admin.TRUE)
                .build();

        Project expectProject = Project.builder()
                .projectId(projectId)
                .title("OriginalTitle")
                .description("OriginalDescription")
                .build();

        // Mocking the repositories
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(expectProject));

        // when
        Project updatedProject = projectCommandService.updateProject(userId, projectId, request);

        // then
        assertEquals(updatedProject.getProjectId(), projectId);
        assertEquals(updatedProject.getTitle(), updateTitle);
        assertEquals(updatedProject.getDescription(), updateDescription);
    }

    @Test
    void updateProjectUnauthorized() {
        // given
        Long projectId = 1L;
        Long userId = 1L;
        String updateTitle = "TestUpdateTitle";
        String updateDescription = "TestUpdateDescription";

        ProjectRequestDTO.UpdateProjectDTO request = ProjectRequestDTO.UpdateProjectDTO.builder()
                .title(updateTitle)
                .description(updateDescription)
                .build();

        User testUser = User.builder()
                .userId(userId)
                .admin(Admin.FALSE) // 관리자 권한이 없도록 설정
                .build();

        // Mocking the user repository
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // when & then
        assertThrows(ProjectHandler.class, () -> {
            projectCommandService.updateProject(userId, projectId, request);
        });
    }

    @Test
    void deleteProject() {
        // given
        Long userId = 1L;
        Long projectId = 1L;
        String title = "testTitle";
        String description = "testDescription";

        User testUser = User.builder()
                .userId(userId)
                .admin(Admin.TRUE)
                .build();

        Project existingProject = Project.builder()
                .projectId(projectId)
                .title(title)
                .description(description)
                .build();

        // Mocking the repositories
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));

        // when
        projectCommandService.deleteProject(userId, projectId);

        // then
        verify(projectRepository).delete(existingProject);
    }

    @Test
    void deleteProjectUnauthorized() {
        // given
        Long userId = 1L;
        Long projectId = 1L;

        User testUser = User.builder()
                .userId(userId)
                .admin(Admin.FALSE) // 관리자 권한이 없도록 설정
                .build();

        // Mocking the user repository
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // when & then
        assertThrows(ProjectHandler.class, () -> {
            projectCommandService.deleteProject(userId, projectId);
        });

        // verify that delete is never called
        verify(projectRepository, never()).delete(any(Project.class));
    }

    @Test
    void addUser() {
        // given
        Long adminId = 1L;
        Long userId = 2L;
        Long projectId = 1L;
        UserRole userRole = UserRole.DEV; // Assuming DEV is an enum value for user roles
        String userName = "testUser";

        User adminUser = User.builder()
                .userId(adminId)
                .admin(Admin.TRUE) // 관리자 권한 설정
                .build();

        User newUser = User.builder()
                .userId(userId)
                .userName(userName)
                .build();

        Project project = Project.builder()
                .projectId(projectId)
                .title("TestProject")
                .description("TestDescription")
                .build();

        ProjectUserRequestDTO.AddUserDTO request = ProjectUserRequestDTO.AddUserDTO.builder()
                .adminId(adminId)
                .projectId(projectId)
                .build();

        ProjectUser expectedProjectUser = ProjectUser.builder()
                .user(newUser)
                .userName(userName)
                .userRole(userRole)
                .project(project)
                .build();

        // Mocking the repositories
        when(userRepository.findById(adminId)).thenReturn(Optional.of(adminUser));
        when(userRepository.findById(userId)).thenReturn(Optional.of(newUser));
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectUserRepository.save(any(ProjectUser.class))).thenReturn(expectedProjectUser);

        // when
        ProjectUser actualProjectUser = projectCommandService.addUser(userId, request);

        // then
        assertEquals(actualProjectUser.getUser().getUserId(), userId);
        assertEquals(actualProjectUser.getProject().getProjectId(), projectId);
        assertEquals(actualProjectUser.getUserName(), userName);
        assertEquals(actualProjectUser.getUserRole(), userRole);

        verify(projectUserRepository).save(any(ProjectUser.class));
    }


    @Test
    void deleteUserInProject() {
        // given
        Long adminId = 1L;
        Long userId = 2L;
        Long projectId = 1L;

        User adminUser = User.builder()
                .userId(adminId)
                .admin(Admin.TRUE) // 관리자 권한 설정
                .build();

        User userToDelete = User.builder()
                .userId(userId)
                .build();

        Project project = Project.builder()
                .projectId(projectId)
                .title("TestProject")
                .description("TestDescription")
                .build();

        // Mocking the repositories
        when(userRepository.findById(adminId)).thenReturn(Optional.of(adminUser));
        when(userRepository.findById(userId)).thenReturn(Optional.of(userToDelete));
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // when
        projectCommandService.deleteUserInProject(userId, projectId, adminId);

        // then
        verify(projectUserRepository).deleteByUserAndProject(userToDelete, project);
    }

}
