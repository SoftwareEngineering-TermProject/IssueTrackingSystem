package com.example.IssueTrackingSystem.service.UserService;

import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.repository.UserRepository;
import com.example.IssueTrackingSystem.web.dto.User.UserRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserQueryServiceImpl userQueryServiceImpl;

    @Test
    @DisplayName("로그인 테스트")
    void signInTest(){
        // given
        Long userId = 1L;

        String userName = "testUserName";
        String password = "testPassword";
        String name = "testName";

        User testUser = User.builder()
                .userId(userId)
                .userName(userName)
                .password(password)
                .name(name)
                .build();

        //given
        UserRequestDTO.SignInRequestDTO request = UserRequestDTO.SignInRequestDTO.builder()
                .userName(userName)
                .password(password)
                .build();

        // projectRepository.findById
        when(userRepository.findByUserName(request.getUserName())).thenReturn(testUser);

        User getUser = userQueryServiceImpl.signIn(request);

        // then
        assertEquals(testUser.getUserId(), getUser.getUserId());
        assertEquals(testUser.getUserName(), getUser.getUserName());
        assertEquals(testUser.getPassword(), getUser.getPassword());
        assertEquals(testUser.getName(), getUser.getName());
    }
}
