package com.example.IssueTrackingSystem.domain.entity;

import com.example.IssueTrackingSystem.domain.common.BaseEntity;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectHashTag;
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
public class Hashtag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtagId;

    @Column(nullable = false, length = 20)
    private String hashtag;

    @OneToMany(mappedBy = "hashtag")
    @Builder.Default
    private List<ProjectHashTag> projectHashTagList = new ArrayList<>();
}