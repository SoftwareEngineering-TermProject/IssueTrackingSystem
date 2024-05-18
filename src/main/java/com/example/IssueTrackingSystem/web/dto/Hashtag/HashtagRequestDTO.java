package com.example.IssueTrackingSystem.web.dto.Hashtag;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HashtagRequestDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HashtagDTO{
        @NotBlank
        String hashtag;
    }
}
