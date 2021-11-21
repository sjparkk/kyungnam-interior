package com.kyungnam.dto;

import com.kyungnam.domain.WorkImage;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkDto {
    private Long id; //pk
    private int category; // 20 30 40.. 평수
    private String title; // 제목
    private String address; // 지역
    private String term; // 기간
    private String budget; // 예산
    private String details; // 세부 공사 내역
    private String thumnailImagePath; // 썸네일 이미지 파일
    private String type; // 아파트 빌라..
    private String content; // 설명내용
    private Boolean isMain; // index 페이지 메인에 노출 여부  true가 노출 총 8개 노출
    private List<WorkImage> workImages; // 이미지 리스트들..
}
