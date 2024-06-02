//package com.example.IssueTrackingSystem.controller;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
//import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
//import com.example.IssueTrackingSystem.domain.entity.Project;
//import com.example.IssueTrackingSystem.repository.ProjectRepository;
//import com.example.IssueTrackingSystem.repository.UserRepository;
//import com.example.IssueTrackingSystem.service.ProjectService.ProjectCommandService;
//import com.example.IssueTrackingSystem.service.ProjectService.ProjectQueryService;
//import com.example.IssueTrackingSystem.web.controller.ProjectController;
//import com.example.IssueTrackingSystem.web.dto.Project.ProjectRequestDTO;
//import com.example.IssueTrackingSystem.web.dto.Project.ProjectResponseDTO;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ProjectController.class)
//class ProjectControllerTest {
//
//    @MockBean
//    private ProjectCommandService projectCommandService;
//
//    @MockBean
//    private ProjectQueryService projectQueryService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private ProjectRepository projectRepository;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void createProject() throws Exception {
//        // given
//        String testTitle = "new project";
//        String testDescription = "new project description";
//
//        Long userId = 1L;
//        Long projectId = 1L;
//        LocalDateTime createdAt = LocalDateTime.now();
//
//        // Request 객체 생성
//        ProjectRequestDTO.CreateProjectRequestDTO projectRequest = ProjectRequestDTO.CreateProjectRequestDTO.builder()
//                .title(testTitle)
//                .description(testDescription)
//                .build();
//
//        // Expect Response 객체 생성
//        Project projectResponse = Project.builder()
//                .projectId(projectId)
//                .title(testTitle)
//                .description(testDescription)
//                .build();
//
//        // Setter 없이 ReflectionTestUtils를 사용하여 필드값을 설정
//        ReflectionTestUtils.setField(projectResponse, "createdAt", createdAt);
//
//        // projectCommandService 에서 createProject 메소드가 호출될 때 projectResponse를 반환하도록 설정
//        when(projectCommandService.createProject(any(Long.class), any(ProjectRequestDTO.CreateProjectRequestDTO.class))).thenReturn(projectResponse);
//
//        // when
//        // Request 객체를 JSON으로(request body로) 변환
//        String body = objectMapper.writeValueAsString(projectRequest);
//
//        // API 호출
//        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post("/projects/")
//                .param("userId", userId.toString())
//                .contentType("application/json")
//                .content(body));
//
//        // then
//        // API 호출 결과가 200 OK인지 확인
//        action.andExpect(status().isOk());
//
//        // API 호출 결과를 ApiResponse 객체로 변환
//        ApiResponse<ProjectResponseDTO.CreateProjectResultDTO> response = objectMapper.readValue(
//                action.andReturn().getResponse().getContentAsString(),
//                new TypeReference<>() {
//                }
//        );
//
//
//        // projectId 확인
//        assertEquals(projectId, response.getResult().getProjectId());
//        // title 확인
//        assertEquals(testTitle, response.getResult().getTitle());
//        // description 확인
//        assertEquals(testDescription, response.getResult().getDescription());
//
//        // createdAt 확인
//        //assertEquals(createdAt, response.getResult().getCreatedAt());
//    }
//}
