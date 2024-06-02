package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.Project;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectUser;
import com.example.IssueTrackingSystem.domain.enums.Admin;
import com.example.IssueTrackingSystem.domain.enums.IssuePriority;
import com.example.IssueTrackingSystem.domain.enums.UserRole;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import com.example.IssueTrackingSystem.repository.ProjectRepository;
import com.example.IssueTrackingSystem.repository.ProjectUserRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IssueCommandServiceImplTest {
    @Mock
    private IssueRepository issueRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectUserRepository projectUserRepository;

    @InjectMocks
    private IssueCommandServiceImpl issueCommandServiceImpl;

    @Test
    @DisplayName("이슈 생성 테스트")
    void createIssue() {
        //when
        Long userId = 1L;
        Long projectId = 1L;
        Long projectUserId = 1L;
        String userName = "testUserName";
        UserRole userRole = UserRole.TESTER;
        String title = "testTitle";
        String description = "testDescription";
        IssuePriority issuePriority = IssuePriority.MAJOR;

        Long issueId = 1L;

        Issue expectIssue = Issue.builder()
                .issueId(issueId)
                .title(title)
                .description(description)
                .issuePriority(issuePriority)
                .build();

        User testUser = User.builder()
                .userId(userId)
                .issueList(new ArrayList<>())
                .build();

        Project testProject = Project.builder()
                .projectId(projectId)
                .title(title)
                .description(description)
                .issueList(new ArrayList<>())
                .projectUserList(new ArrayList<>())
                .build();

        ProjectUser testProjectUser = ProjectUser.builder()
                .ProjectUserId(projectUserId)
                .userName(userName)
                .userRole(userRole)
                .build();

        testProject.setUser(testUser);
        testProjectUser.setUser(testUser);
        testProjectUser.setProject(testProject);
        expectIssue.setUser(testUser);
        expectIssue.setProject(testProject);

        // userRepository.findById
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        // projectRepository.findById
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(testProject));
        // projectUserRepository.findById
        when(projectUserRepository.findByUser_UserIdAndProject_ProjectId(userId, projectId)).thenReturn(testProjectUser);
        // issueRepository.save
        when(issueRepository.save(any(Issue.class))).thenReturn(expectIssue);

        //given
        IssueRequestDTO.CreateIssueRequestDTO request = IssueRequestDTO.CreateIssueRequestDTO.builder()
                .projectId(projectId)
                .title(title)
                .description(description)
                .issuePriority(issuePriority)
                .build();

        Issue issue = issueCommandServiceImpl.createIssue(userId, request);

        //then
        // 검증
        assertEquals(issue.getIssueId(), issueId);
        assertEquals(issue.getTitle(), title);
        assertEquals(issue.getDescription(), description);
        assertEquals(issue.getIssuePriority(), issuePriority);

        // 연관관계 검증
        assertEquals(issue.getUser().getUserId(), userId);
        assertEquals(issue.getProject().getProjectId(), projectId);
        assertEquals(testProject.getUser().getUserId(), userId);
    }

    @Test
    @DisplayName("이슈 업데이트 테스트")
    void updateIssue() {
        //when
        Long userId = 1L;
        Long projectId = 1L;
        String title = "testTitle";
        String description = "testDescription";
        IssuePriority issuePriority = IssuePriority.MAJOR;

        Long issueId = 1L;

        Issue expectIssue = Issue.builder()
                .issueId(issueId)
                .title(title)
                .description(description)
                .issuePriority(issuePriority)
                .build();

        User testUser = User.builder()
                .userId(userId)
                .issueList(new ArrayList<>())
                .admin(Admin.TRUE)
                .build();

        Project testProject = Project.builder()
                .projectId(projectId)
                .title(title)
                .description(description)
                .issueList(new ArrayList<>())
                .projectUserList(new ArrayList<>())
                .build();

        testProject.setUser(testUser);
        expectIssue.setUser(testUser);
        expectIssue.setProject(testProject);

        when(issueRepository.findById(issueId)).thenReturn(Optional.of(expectIssue));
        // userRepository.findById
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        //given
        IssueRequestDTO.UpdateIssueDTO request = IssueRequestDTO.UpdateIssueDTO.builder()
                .title(title)
                .description(description)
                .build();

        Issue issue = issueCommandServiceImpl.updateIssue(issueId, userId, request);

        //then
        // verify
        assertEquals(issue.getIssueId(), issueId);
        assertEquals(issue.getTitle(), title);
        assertEquals(issue.getDescription(), description);

        // 연관관계 검증
        assertEquals(issue.getUser().getUserId(), userId);
        assertEquals(issue.getProject().getProjectId(), projectId);

    }

    @DisplayName("이슈 삭제 테스트")
    @Test
    void deleteQuestion() {
        //when
        Long userId = 1L;
        String title = "testTitle";
        String description = "testDescription";
        IssuePriority issuePriority = IssuePriority.MAJOR;

        Long issueId = 1L;

        Issue expectIssue = Issue.builder()
                .issueId(issueId)
                .title(title)
                .description(description)
                .issuePriority(issuePriority)
                .build();

        User testUser = User.builder()
                .userId(userId)
                .issueList(new ArrayList<>())
                .admin(Admin.TRUE)
                .build();

        when(issueRepository.findById(issueId)).thenReturn(Optional.of(expectIssue));
        // userRepository.findById
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        //질문 삭제
        issueCommandServiceImpl.deleteIssue(issueId, userId);

        //verify
        verify(issueRepository).delete(expectIssue);

    }

    @Test
    @DisplayName("이슈 담당자 추가 테스트")
    void addAssignee() {
        //when
        Long adminId = 1L;
        Long plId = 2L;
        Long devId = 3L;
        Long projectId = 1L;
        Long projectUserId = 1L;
        String userName = "testUserName";
        UserRole userRole = UserRole.PL;
        String title = "testTitle";
        String description = "testDescription";
        IssuePriority issuePriority = IssuePriority.MAJOR;

        Long issueId = 1L;

        Issue expectIssue = Issue.builder()
                .issueId(issueId)
                .title(title)
                .description(description)
                .issuePriority(issuePriority)
                .assignee(userName)
                .build();

        User testADIMIN = User.builder()
                .userId(adminId)
                .issueList(new ArrayList<>())
                .admin(Admin.TRUE)
                .build();

        User testPL = User.builder()
                .userId(plId)
                .issueList(new ArrayList<>())
                .build();

        User testDEV = User.builder()
                .userId(devId)
                .userName(userName)
                .issueList(new ArrayList<>())
                .build();

        Project testProject = Project.builder()
                .projectId(projectId)
                .title(title)
                .description(description)
                .issueList(new ArrayList<>())
                .projectUserList(new ArrayList<>())
                .build();

        ProjectUser testProjectUser = ProjectUser.builder()
                .ProjectUserId(projectUserId)
                .userName(userName)
                .userRole(userRole)
                .build();

        // admin 계정이 담장자 추가하는 경우
        testProject.setUser(testADIMIN);
        testProjectUser.setProject(testProject);
        expectIssue.setUser(testADIMIN);
        expectIssue.setProject(testProject);

        IssueRequestDTO.AssigneeRequestDTO request = IssueRequestDTO.AssigneeRequestDTO.builder()
                .userName(userName)
                .build();

        // userRepository.findById
        when(userRepository.findById(adminId)).thenReturn(Optional.of(testADIMIN));
        // userRepository.findById
        when(userRepository.findByUserName(userName)).thenReturn(testDEV);
        // issueRepository.findById
        when(issueRepository.findById(issueId)).thenReturn(Optional.of(expectIssue));
        // issueRepository.save
        when(issueRepository.save(any(Issue.class))).thenReturn(expectIssue);

        Issue issue = issueCommandServiceImpl.addAssignee(adminId, issueId, request);

        //then
        // 검증
        assertEquals(issue.getIssueId(), issueId);
        assertEquals(issue.getTitle(), title);
        assertEquals(issue.getDescription(), description);
        assertEquals(issue.getIssuePriority(), issuePriority);
        assertEquals(issue.getAssignee(), userName);

        // 연관관계 검증
        assertEquals(issue.getUser().getUserId(), adminId);
        assertEquals(issue.getProject().getProjectId(), projectId);
        assertEquals(testProject.getUser().getUserId(), adminId);

        // PL 계정이 담장자 추가하는 경우
        testProject.setUser(testPL);
        testProjectUser.setUser(testPL);
        expectIssue.setUser(testPL);
        expectIssue.setProject(testProject);

        // userRepository.findById
        when(userRepository.findById(plId)).thenReturn(Optional.of(testPL));
        // userRepository.findById
        when(projectUserRepository.findByUser(testPL)).thenReturn(testProjectUser);
        // userRepository.findById
        when(userRepository.findByUserName(userName)).thenReturn(testDEV);
        // issueRepository.findById
        when(issueRepository.findById(issueId)).thenReturn(Optional.of(expectIssue));
        // issueRepository.save
        when(issueRepository.save(any(Issue.class))).thenReturn(expectIssue);

        Issue issue2 = issueCommandServiceImpl.addAssignee(plId, issueId, request);

        //then
        // 검증
        assertEquals(issue2.getIssueId(), issueId);
        assertEquals(issue2.getTitle(), title);
        assertEquals(issue2.getDescription(), description);
        assertEquals(issue2.getIssuePriority(), issuePriority);
        assertEquals(issue2.getAssignee(), userName);

        // 연관관계 검증
        assertEquals(issue2.getUser().getUserId(), plId);
        assertEquals(issue2.getProject().getProjectId(), projectId);
        assertEquals(testProject.getUser().getUserId(), plId);
    }

    @Test
    @DisplayName("이슈 수정자 추가 테스트")
    void addFixer() {
        //when
        Long adminId = 1L;
        Long plId = 2L;
        Long devId = 3L;
        Long projectId = 1L;
        String userName = "testUserName";
        String title = "testTitle";
        String description = "testDescription";
        IssuePriority issuePriority = IssuePriority.MAJOR;

        Long issueId = 1L;

        Issue expectIssue = Issue.builder()
                .issueId(issueId)
                .title(title)
                .description(description)
                .issuePriority(issuePriority)
                .assignee(userName)
                .fixer(userName)
                .build();

        User testADIMIN = User.builder()
                .userId(adminId)
                .issueList(new ArrayList<>())
                .admin(Admin.TRUE)
                .build();

        User testPL = User.builder()
                .userId(plId)
                .issueList(new ArrayList<>())
                .build();

        User testDEV = User.builder()
                .userId(devId)
                .userName(userName)
                .issueList(new ArrayList<>())
                .build();

        Project testProject = Project.builder()
                .projectId(projectId)
                .title(title)
                .description(description)
                .issueList(new ArrayList<>())
                .projectUserList(new ArrayList<>())
                .build();

        // admin 계정이 수정자 추가하는 경우
        testProject.setUser(testADIMIN);
        expectIssue.setUser(testADIMIN);
        expectIssue.setProject(testProject);

        // userRepository.findById
        when(userRepository.findById(adminId)).thenReturn(Optional.of(testADIMIN));
        // issueRepository.findById
        when(issueRepository.findById(issueId)).thenReturn(Optional.of(expectIssue));
        // issueRepository.save
        when(issueRepository.save(any(Issue.class))).thenReturn(expectIssue);

        Issue issue = issueCommandServiceImpl.addFixer(adminId, issueId);

        //then
        // 검증
        assertEquals(issue.getIssueId(), issueId);
        assertEquals(issue.getTitle(), title);
        assertEquals(issue.getDescription(), description);
        assertEquals(issue.getIssuePriority(), issuePriority);
        assertEquals(issue.getFixer(), userName);

        // 연관관계 검증
        assertEquals(issue.getUser().getUserId(), adminId);
        assertEquals(issue.getProject().getProjectId(), projectId);
        assertEquals(testProject.getUser().getUserId(), adminId);

        // Assignee 본인이 수정자 추가하는 경우
        testProject.setUser(testPL);
        expectIssue.setUser(testPL);
        expectIssue.setProject(testProject);

        // userRepository.findById
        when(userRepository.findById(devId)).thenReturn(Optional.of(testDEV));
        // userRepository.findById
        // issueRepository.findById
        when(issueRepository.findById(issueId)).thenReturn(Optional.of(expectIssue));
        // issueRepository.save
        when(issueRepository.save(any(Issue.class))).thenReturn(expectIssue);

        Issue issue2 = issueCommandServiceImpl.addFixer(devId, issueId);

        //then
        // 검증
        assertEquals(issue2.getIssueId(), issueId);
        assertEquals(issue2.getTitle(), title);
        assertEquals(issue2.getDescription(), description);
        assertEquals(issue2.getIssuePriority(), issuePriority);
        assertEquals(issue2.getFixer(), userName);

        // 연관관계 검증
        assertEquals(issue2.getUser().getUserId(), plId);
        assertEquals(issue2.getProject().getProjectId(), projectId);
        assertEquals(testProject.getUser().getUserId(), plId);
    }

    @Test
    @DisplayName("이슈 상태 업데이트 테스트")
    void updateIssueStatus() {
        //when
        Long userId = 1L;
        Long projectId = 1L;
        String title = "testTitle";
        String description = "testDescription";
        IssuePriority issuePriority = IssuePriority.MAJOR;

        Long issueId = 1L;

        Issue expectIssue = Issue.builder()
                .issueId(issueId)
                .title(title)
                .description(description)
                .issuePriority(issuePriority)
                .build();

        User testUser = User.builder()
                .userId(userId)
                .issueList(new ArrayList<>())
                .admin(Admin.TRUE)
                .build();

        Project testProject = Project.builder()
                .projectId(projectId)
                .title(title)
                .description(description)
                .issueList(new ArrayList<>())
                .projectUserList(new ArrayList<>())
                .build();

        testProject.setUser(testUser);
        expectIssue.setUser(testUser);
        expectIssue.setProject(testProject);

        when(issueRepository.findById(issueId)).thenReturn(Optional.of(expectIssue));
        // userRepository.findById
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        //given
        IssueRequestDTO.UpdateIssueDTO request = IssueRequestDTO.UpdateIssueDTO.builder()
                .title(title)
                .description(description)
                .build();

        Issue issue = issueCommandServiceImpl.updateIssue(issueId, userId, request);

        //then
        // verify
        assertEquals(issue.getIssueId(), issueId);
        assertEquals(issue.getTitle(), title);
        assertEquals(issue.getDescription(), description);

        // 연관관계 검증
        assertEquals(issue.getUser().getUserId(), userId);
        assertEquals(issue.getProject().getProjectId(), projectId);

    }
}
