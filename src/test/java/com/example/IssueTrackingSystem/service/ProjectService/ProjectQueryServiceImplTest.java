package com.example.IssueTrackingSystem.service.ProjectService;

import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectQueryServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectQueryServiceImpl projectQueryService;

    @Test
    void projectFind() {
        // given
        Long projectId = 1L;
        ProjectRequestDTO.GetProjectRequestDTO request = new ProjectRequestDTO.GetProjectRequestDTO(projectId);
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        // when
        Project foundProject = projectQueryService.projectFind(request);

        // then
        assertNull(foundProject);
    }

    @Test
    void findAllBySearch() {
        // given
        String searchKeyword = "test";
        Optional<String> optSearch = Optional.of(searchKeyword);
        List<Project> projects = List.of(
                Project.builder().title("Test Project 1").build(),
                Project.builder().title("Test Project 2").build()
        );

        when(projectRepository.findAllByTitleContainingIgnoreCaseOrderByCreatedAtDesc(searchKeyword)).thenReturn(projects);

        // when
        List<Project> foundProjects = projectQueryService.findAllBySearch(optSearch);

        // then
        assertEquals(foundProjects, projects);
    }

    @Test
    void findAllBySearch_NoSearchKeyword() {
        // given
        Optional<String> optSearch = Optional.empty();
        List<Project> projects = List.of(
                Project.builder().title("Test Project 1").build(),
                Project.builder().title("Test Project 2").build()
        );

        when(projectRepository.findAllByOrderByCreatedAtDesc()).thenReturn(projects);

        // when
        List<Project> foundProjects = projectQueryService.findAllBySearch(optSearch);

        // then
        assertEquals(foundProjects, projects);
    }

    @Test
    void getProject() {
        // given
        Long projectId = 1L;
        Project project = Project.builder()
                .projectId(projectId)
                .build();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // when
        Project foundProject = projectQueryService.getProject(projectId);

        // then
        assertEquals(foundProject, project);
    }
}
