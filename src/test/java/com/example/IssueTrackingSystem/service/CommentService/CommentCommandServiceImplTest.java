package com.example.IssueTrackingSystem.service.CommentService;

import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.repository.CommentRepository;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentCommandServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private CommentCommandServiceImpl commentCommandService;

    // 코멘트 생성 TEST
    @Test
    void createComment() {
        // when
        Long userId = 1L;
        Long issueId = 1L;
        String content = "testContent";

        Long commentId = 1L;

        Comment expectComment = Comment.builder()
                .commentId(commentId)
                .comment(content)
                .build();

        User testUser = User.builder()
                .userId(userId)
                .build();

        expectComment.setUser(testUser);

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // 이슈 객체 생성
        Issue testIssue = Issue.builder()
                .issueId(issueId)
                .build();

        when(issueRepository.findById(issueId)).thenReturn(Optional.of(testIssue));

        when(commentRepository.save(any(Comment.class))).thenReturn(expectComment);

        // given
        CommentRequestDTO.CreateCommentRequestDTO request = CommentRequestDTO.CreateCommentRequestDTO.builder()
                .issueId(issueId)
                .content(content)
                .build();

        Comment comment = commentCommandService.createComment(userId, request);

        // then
        assertEquals(comment.getCommentId(), commentId);
        assertEquals(comment.getComment(), content); // 코멘트 내용 비교 수정

// 연관관계 검증
        assertEquals(comment.getUser().getUserId(), userId);
    }
}
