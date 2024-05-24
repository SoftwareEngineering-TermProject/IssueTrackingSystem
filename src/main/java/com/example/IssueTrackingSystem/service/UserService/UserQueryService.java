package com.example.IssueTrackingSystem.service.UserService;

import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.web.dto.User.UserRequestDTO;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    User signIn(UserRequestDTO.SignInRequestDTO request);

    List<User> findAllBySearch(Optional<String> optSearch);
}
