package com.example.IssueTrackingSystem.service.IssueService;

import com.example.IssueTrackingSystem.domain.entity.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueQueryService {
    List<Issue> findAllBySearch(Optional<String> optSearch);
}
