package com.kninterior.domain;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "contact")
public class Contact extends Date{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk
    private String name; // 고객명
    private String phone; // 번호
    private String email; // 이메일
    private String postCode; // 우편번호
    private String address; // 주소
    private String addressDetail; // 상세주소
    private String structureType; // 건물구분
    private int roomCnt; // 방개수
    private int bathroomCnt; // 화장실개수
    private int sizeNum; // 평수 or 면적
    private String sizeType; // 타입에 따라 평수 or 면적 -> 면적일시 평수로 변환해주는 로직
    private String budget; // 예산
    private String callTime; // 통화 가능 시간
    private String password; // 게시글 비밀번호
    private String description; // 문의내용
    private String accessRoute; // 접근 경로
    private String salt; // 솔트값


    public void saveHashPassword(String transPwd) {
        this.password = transPwd;
    }

    public void createSalt(String salt) {
        this.salt = salt;
    }

}
