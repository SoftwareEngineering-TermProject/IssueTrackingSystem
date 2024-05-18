package com.example.IssueTrackingSystem.web.dto.Hashtag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HashtagResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HashtagReturnDTO{
        Long hashtagId;
        String hashtag;
    }
}
