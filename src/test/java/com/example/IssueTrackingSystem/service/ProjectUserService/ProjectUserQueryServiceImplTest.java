package com.example.IssueTrackingSystem.service.ProjectUserService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.repository.ProjectUserRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectUserQueryServiceImplTest {

    @Mock
    private ProjectUserRepository projectUserRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectUserQueryServiceImpl projectUserQueryService;

    @Test
    void testGetProjectUser() {
        // given
        Long projectId = 1L;
        Project project = mock(Project.class);
        project.setId(projectId);
        List<ProjectUser> projectUserList = List.of(
                mock(ProjectUser.class),
                mock(ProjectUser.class)
        );
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectUserRepository.findAllByProject(project)).thenReturn(projectUserList);

        // when
        List<ProjectUser> result = projectUserQueryService.getProjectUser(projectId);

        // then
        assertEquals(projectUserList, result);
    }

    @Test
    void testGetUserProject() {
        // given
        Long userId = 1L;
        User user = mock(User.class);
        user.setId(userId);
        List<ProjectUser> userProjectList = List.of(
                mock(ProjectUser.class),
                mock(ProjectUser.class)
        );
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(projectUserRepository.findAllByUser(user)).thenReturn(userProjectList);

        // when
        List<ProjectUser> result = projectUserQueryService.getUserProject(userId);

        // then
        assertEquals(userProjectList, result);
    }
}
