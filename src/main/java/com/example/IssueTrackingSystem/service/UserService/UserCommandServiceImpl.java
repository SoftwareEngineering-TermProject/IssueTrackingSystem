package com.example.IssueTrackingSystem.service.UserService;

import com.example.IssueTrackingSystem.converter.UserConverter;
import com.example.IssueTrackingSystem.domain.User;
import com.example.IssueTrackingSystem.repository.UserRepository;
import com.example.IssueTrackingSystem.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserCommandServiceImpl implements UserCommandService{

    private final UserRepository userRepository;
    public User createUser(UserRequestDTO.CreateUserRequestDTO request){
        User newUser = UserConverter.toUser(request);
        User savedUser = userRepository.save(newUser);

        return savedUser;
    }
}
