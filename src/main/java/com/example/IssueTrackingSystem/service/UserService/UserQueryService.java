package com.example.IssueTrackingSystem.service.UserService;

import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.web.dto.User.UserRequestDTO;

public interface UserQueryService {

    User signIn(UserRequestDTO.SignInRequestDTO request);
}
