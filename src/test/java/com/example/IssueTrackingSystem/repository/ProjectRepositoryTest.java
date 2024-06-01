//package com.example.IssueTrackingSystem.repository;
//
//import com.example.IssueTrackingSystem.domain.entity.Project;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static junit.framework.TestCase.assertEquals;
//
//@DataJpaTest
//public class ProjectRepositoryTest {
//    private final ProjectRepository projectRepository;
//
//    @Autowired
//    public ProjectRepositoryTest(ProjectRepository projectRepository) {
//        this.projectRepository = projectRepository;
//    }
//
//    @Test
//    public void saveProjectTest() {
//        // given
//        String title = "testTitle";
//
//        // when
//        projectRepository.save(Project.builder()
//                .title(title)
//                .build());
//
//        // then
//        List<Project> allProject = projectRepository.findAllByOrderByCreatedAtDesc();
//
//        assertEquals(1, allProject.size());
//        assertEquals(title, allProject.get(0).getTitle());
//    }
//}