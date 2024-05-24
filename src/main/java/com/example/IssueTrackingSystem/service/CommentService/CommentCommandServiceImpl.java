package com.example.IssueTrackingSystem.service.CommentService;


import com.example.IssueTrackingSystem.converter.CommentConverter;
import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Issue;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.repository.CommentRepository;
import com.example.IssueTrackingSystem.repository.IssueRepository;
import com.example.IssueTrackingSystem.repository.UserRepository;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentCommandServiceImpl implements CommentCommandService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final IssueRepository issueRepository;
    public Comment createComment(Long userId, CommentRequestDTO.CreateCommentRequestDTO request) {

        Comment newComment = CommentConverter.toComment(request);
        User getUser = userRepository.findById(userId).get();
        Issue getIssue = issueRepository.findById(request.getIssueId()).get();
        newComment.setUser(getUser);
        newComment.setIssue(getIssue);

        Comment savedComment = commentRepository.save(newComment);

        return savedComment;
    }

    @Override
    public Comment updateComment(Long userId, Long commentId, CommentRequestDTO.UpdateCommentDTO request) {

        User getUser = userRepository.findById(userId).get();

        Comment updateComment = commentRepository.findById(commentId).get();
        updateComment.update(request);

        return updateComment;
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {

        User getUser = userRepository.findById(userId).get();

        Comment deleteComment = commentRepository.findById(commentId).get();
        commentRepository.delete(deleteComment);
    }
}

