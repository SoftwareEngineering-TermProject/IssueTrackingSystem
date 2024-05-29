package com.example.IssueTrackingSystem.apiPayload.code.status;

import com.example.IssueTrackingSystem.apiPayload.code.BaseErrorCode;
import com.example.IssueTrackingSystem.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 회원 관려 에러 1000
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER_1001", "사용자가 없습니다."),
    NAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "USER_1002", "이름입력은 필수 입니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER_1003", "이미 존재하는 유저입니다."),
    USER_ID_NULL(HttpStatus.BAD_REQUEST, "USER_1004", "사용자 아이디는 필수 입니다."),
    USER_ADMIN_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "USER_1005", "관리자 권한이 없습니다."),

    // 프로젝트 관려 에러 2000
    PROJECT_CREATE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "PROJECT_2001", "프로젝트 생성 권한이 없습니다."),
    PROJECT_UPDATE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "PROJECT_2002", "프로젝트 수정 권한이 없습니다."),
    PROJECT_DELETE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "PROJECT_2003", "프로젝트 삭제 권한이 없습니다."),
    PROJECT_ADDUSER_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "PROJECT_2004", "프로젝트에 사용자를 추가할 권한이 없습니다."),
    PROJECT_USER_DELETE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "PROJECT_2005", "프로젝트 참여자 삭제 권한이 없습니다."),

    // 이슈 관련 에러 3000
    ISSUE_CREATE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "ISSUE_3001", "이슈 생성 권한이 없습니다."),
    ISSUE_UPDATE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "ISSUE_3002", "이슈 수정 권한이 없습니다."),
    ISSUE_DELETE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "ISSUE_3003", "이슈 삭제 권한이 없습니다."),
    ISSUE_ASSIGNEE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "ISSUE_3004", "담당자 지정 권한이 없습니다."),
    ISSUE_ASSIGNEE_NOT_FOUND(HttpStatus.BAD_REQUEST, "ISSUE_3005", "지정할 담당자가 존재하지 않습니다."),
    ISSUE_FIXER_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "ISSUE_3006", "수정자 지정 권한이 없습니다."),
    ISSUE_DELETE_ASSIGNEE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "ISSUE_3007", "담당자 삭제 권한이 없습니다."),
    ISSUE_DELETE_FIXER_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "ISSUE_3008", "수정자 삭제 권한이 없습니다."),
    USER_DEV_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "ISSUE_3009", "사용자가 DEV역할이 아닙니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
