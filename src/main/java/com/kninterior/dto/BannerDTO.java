package com.kninterior.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BannerDTO {
    private Long id;
    private String imagePath;
    private int viewOrder;
}
