package com.example.IssueTrackingSystem.domain.entity;

import com.example.IssueTrackingSystem.domain.common.BaseEntity;
import com.example.IssueTrackingSystem.domain.enums.IssueStatus;
import com.example.IssueTrackingSystem.web.dto.Issue.IssueRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Issue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'NEW'")
    private IssueStatus issueStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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

    public void updateIssue(IssueRequestDTO.UpdateIssueDTO request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
    }
}
