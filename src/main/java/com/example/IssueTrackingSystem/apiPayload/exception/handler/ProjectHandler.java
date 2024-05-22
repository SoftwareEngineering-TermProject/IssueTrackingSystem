package com.example.IssueTrackingSystem.apiPayload.exception.handler;

import com.example.IssueTrackingSystem.apiPayload.code.BaseErrorCode;
import com.example.IssueTrackingSystem.apiPayload.exception.GeneralException;

public class ProjectHandler extends GeneralException {
    public ProjectHandler(BaseErrorCode baseErrorCode){
        super(baseErrorCode);
    }
}
