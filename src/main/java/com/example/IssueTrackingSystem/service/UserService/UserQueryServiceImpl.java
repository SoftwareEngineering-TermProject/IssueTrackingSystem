package com.example.IssueTrackingSystem.service.UserService;

import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.repository.UserRepository;
import com.example.IssueTrackingSystem.web.dto.User.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserQueryServiceImpl implements UserQueryService{

    private final UserRepository userRepository;
    public User signIn(UserRequestDTO.SignInRequestDTO request){
        User findUser = userRepository.findByUserName(request.getUserName());
        if(findUser != null){
            if(request.getPassword().equals(findUser.getPassword())) return findUser; // 예외처리 해주기
        }
        return null;
    }

    public List<User> findAllBySearch(Optional<String> optSearch) {
        // 만약 검색어가 존재한다면
        if (optSearch.isPresent()) {
            String search = optSearch.get();

            return userRepository.findAllByUserNameContainingIgnoreCaseOrderByCreatedAtDesc(search);
        }
        // 검색어 존재 X
        return userRepository.findAllByOrderByCreatedAtDesc();
    }
}
