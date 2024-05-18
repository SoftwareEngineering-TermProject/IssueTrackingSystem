package com.example.IssueTrackingSystem.apiPayload.exception.handler;

import com.example.IssueTrackingSystem.apiPayload.code.BaseErrorCode;
import com.example.IssueTrackingSystem.apiPayload.exception.GeneralException;

public class IssueHandler extends GeneralException {
    public IssueHandler(BaseErrorCode baseErrorCode){
        super(baseErrorCode);
    }
}
