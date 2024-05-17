package com.example.IssueTrackingSystem.domain.entity.mapping;

import com.example.IssueTrackingSystem.domain.entity.Hashtag;
import com.example.IssueTrackingSystem.domain.entity.Project;
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
public class ProjectHashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProjectHashtagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")

    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}