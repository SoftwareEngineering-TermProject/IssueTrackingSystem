package com.example.IssueTrackingSystem.domain.entity.mapping;


import com.example.IssueTrackingSystem.domain.entity.Comment;
import com.example.IssueTrackingSystem.domain.entity.Hashtag;
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
public class CommentHashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CommentHashtagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")

    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}
