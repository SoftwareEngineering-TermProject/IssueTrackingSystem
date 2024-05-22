package com.example.IssueTrackingSystem.domain.entity;

import com.example.IssueTrackingSystem.domain.common.BaseEntity;
import com.example.IssueTrackingSystem.domain.entity.mapping.CommentHashTag;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectHashTag;
import com.example.IssueTrackingSystem.web.dto.Comment.CommentRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CommentHashTag> commentHashTagList = new ArrayList<>();

    public void setUser(User user) {
//        // 기존에 이미 등록되어 있던 관계를 제거
//        if (this.user != null) {
//            this.user.getQuestionList().remove(this);
//        }

        this.user = user;

//        // 양방향 관계를 설정
//        if (user != null) {
//            user.getQuestionList().add(this);
//        }
    }

    public void setIssue(Issue issue) {
//        // 기존에 이미 등록되어 있던 관계를 제거
//        if (this.user != null) {
//            this.user.getQuestionList().remove(this);
//        }

        this.issue = issue;

//        // 양방향 관계를 설정
//        if (user != null) {
//            user.getQuestionList().add(this);
//        }
    }

    public void update(CommentRequestDTO.UpdateCommentDTO request) {
        this.comment = request.getContent();;
    }

}
