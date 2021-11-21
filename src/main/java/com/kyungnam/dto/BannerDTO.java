package com.kyungnam.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BannerDTO {
    private Long id;
    private String imagePath; // 이미지파일
    private int viewOrder; // 순서
}
