package com.example.IssueTrackingSystem.service.UserService;

import com.example.IssueTrackingSystem.domain.User;
import com.example.IssueTrackingSystem.web.dto.UserRequestDTO;

public interface UserQueryService {

    User signIn(UserRequestDTO.SignInRequestDTO request);
}
