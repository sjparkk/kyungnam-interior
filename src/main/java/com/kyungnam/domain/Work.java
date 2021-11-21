package com.kyungnam.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"workImages"})
@Table(name = "work")
@Builder
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
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

    @OneToMany(mappedBy = "work")
    private List<WorkImage> workImages = new ArrayList<>();

    public Work(int category, String title, String thumnailImagePath, String type, String content) {
        this.category = category;
        this.title = title;
        this.thumnailImagePath = thumnailImagePath;
        this.type = type;
        this.content = content;
    }

    public Work(int category, String title, String thumnailImagePath, String type, boolean isMain) {
        this.category = category;
        this.title = title;
        this.thumnailImagePath = thumnailImagePath;
        this.type = type;
        this.isMain = isMain;
    }

}
