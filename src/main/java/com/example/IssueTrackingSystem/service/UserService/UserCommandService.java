package com.example.IssueTrackingSystem.service.UserService;

import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.web.dto.User.UserRequestDTO;

public interface UserCommandService {
    User createUser(UserRequestDTO.CreateUserRequestDTO request);
}
