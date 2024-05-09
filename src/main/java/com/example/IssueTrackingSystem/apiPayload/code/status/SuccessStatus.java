package com.example.IssueTrackingSystem.apiPayload.code.status;

import com.example.IssueTrackingSystem.apiPayload.code.BaseCode;
import com.example.IssueTrackingSystem.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 유저 관련 응답
    User_OK(HttpStatus.OK, "USER_1000", "성공입니다."),

    // 프로젝트 관련 응답
    Project_OK(HttpStatus.OK, "PROJECT_2000", "성공입니다."),

    // 이슈 관련 응답
    Issue_OK(HttpStatus.OK, "ISSUE_3000", "성공입니다."),

    // 코멘트 관련 응답
    Comment_OK(HttpStatus.OK, "COMMENT_4000", "성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
