package com.example.IssueTrackingSystem.service.CommentService;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.repository.CommentRepository;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentQueryServiceImplTest {

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentQueryServiceImpl commentQueryService;

    @Test
    void getCommentsList() {
        // given
        Issue issue = Issue.builder().issueId(1L).build();
        List<Comment> comments = List.of(
                Comment.builder().commentId(1L).comment("Test Comment 1").issue(issue).build(),
                Comment.builder().commentId(2L).comment("Test Comment 2").issue(issue).build()
        );

        when(commentRepository.findAllByIssueOrderByCreatedAtDesc(issue)).thenReturn(comments);

        // when
        List<Comment> foundComments = commentQueryService.getCommentsList(issue);

        // then
        assertEquals(foundComments, comments);
    }
}
