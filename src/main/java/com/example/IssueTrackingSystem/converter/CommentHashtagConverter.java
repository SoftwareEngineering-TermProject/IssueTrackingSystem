package com.example.IssueTrackingSystem.converter;

import com.example.IssueTrackingSystem.domain.entity.Hashtag;
import com.example.IssueTrackingSystem.domain.entity.mapping.CommentHashTag;

import java.util.List;
import java.util.stream.Collectors;

public class CommentHashtagConverter {

    public static List<Hashtag> toHashtagList(List<CommentHashTag> list) {
        return list.stream()
                .map(CommentHashTag::getHashtag)
                .collect(Collectors.toList());
    }
}
