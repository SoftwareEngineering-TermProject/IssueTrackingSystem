package com.example.IssueTrackingSystem.service.UserService;

import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.web.dto.UserRequestDTO;

public interface UserCommandService {
    User createUser(UserRequestDTO.CreateUserRequestDTO request);
}
