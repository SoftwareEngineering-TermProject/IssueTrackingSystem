package com.example.IssueTrackingSystem.web.dto.Project;

import com.example.IssueTrackingSystem.web.dto.Hashtag.HashtagResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProjectResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateProjectResultDTO {
        Long projectId;
        String title;
        String description;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectDTO {
        Long projectId;
        String title;
        String description;
        // 이슈 상태?
        // 코멘트?
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectPreviewDTO {  // 특정 프로젝트 조회
        Long projectId;
        String title;
        String description;
        Integer projectCount;
        List<HashtagResponseDTO.HashtagReturnDTO> hashtags;
        // 이슈 상태?
        // 코멘트?
        // UserResponseDTO.UserPreviewInQuestionDTO user; ?
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectPreviewListDTO {  // 전체 프로젝트 리스트 조회
        List<ProjectPreviewDTO> projects;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        boolean isFirst;
        boolean isLast;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetProjectResultDTO {
        Long projectId;
        String title;
        String description;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateProjectResultDTO {
        Long projectId;
        String title;
        String description;

    }

}
