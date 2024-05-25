package com.example.IssueTrackingSystem.apiPayload.exception.handler;

import com.example.IssueTrackingSystem.apiPayload.code.BaseErrorCode;
import com.example.IssueTrackingSystem.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode baseErrorCode){
        super(baseErrorCode);
    }
}
