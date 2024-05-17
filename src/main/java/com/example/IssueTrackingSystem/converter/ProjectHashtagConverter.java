package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Hashtag;
import com.example.IssueTrackingSystem.domain.entity.mapping.ProjectHashTag;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectHashtagConverter {

    public static List<Hashtag> toHashtagList(List<ProjectHashTag> list) {
        return list.stream()
                .map(ProjectHashTag::getHashtag)
                .collect(Collectors.toList());
    }
}
