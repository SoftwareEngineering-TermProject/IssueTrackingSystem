package com.example.IssueTrackingSystem.web.controller;

import com.example.IssueTrackingSystem.apiPayload.ApiResponse;
import com.example.IssueTrackingSystem.apiPayload.code.status.SuccessStatus;
import com.example.IssueTrackingSystem.converter.UserConverter;
import com.example.IssueTrackingSystem.domain.entity.User;
import com.example.IssueTrackingSystem.service.UserService.UserCommandService;
import com.example.IssueTrackingSystem.service.UserService.UserQueryService;
import com.example.IssueTrackingSystem.web.dto.User.UserRequestDTO;
import com.example.IssueTrackingSystem.web.dto.User.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @Operation(summary = "회원가입", description =
            "User를 생성합니다."
    )
    @PostMapping("/sign_up")
    public ApiResponse<UserResponseDTO.CreateUserResultDTO> signUp(
            @RequestBody UserRequestDTO.CreateUserRequestDTO request
            ) {
        User newUser = userCommandService.createUser(request);
        return ApiResponse.onSuccess(
                SuccessStatus.User_OK,
                UserConverter.toCreateResultDTO(newUser)
        );
    }

    @Operation(summary = "로그인", description =
            "로그인"
    )
    @PostMapping("/sign_in")
    public ApiResponse<UserResponseDTO.SignInResultDTO> signIn(
            @RequestBody UserRequestDTO.SignInRequestDTO request
            ) {
        User findUser = userQueryService.signIn(request);
        return ApiResponse.onSuccess(
                SuccessStatus.User_OK,
                UserConverter.toSignInResultDTO(findUser)
        );
    }


    // 유저 리스트 검색
    @GetMapping
    @Operation(
            summary = "유저 리스트 검색 API"
            , description = "유저 전체 리스트를 조회하거나 특정 유저를 검색할 수 있습니다."
    )
    public ApiResponse<?> getUsers(
            @RequestParam Optional<String> search
    ) {
        List<User> Users = userQueryService.findAllBySearch(search);
        return ApiResponse.onSuccess(
                SuccessStatus.User_OK,
                UserConverter.toUserPreviewListDTO(Users)
        );
    }
}
