package com.kninterior.service;

import com.kninterior.domain.Banner;
import com.kninterior.dto.BannerDTO;

import java.util.List;

public interface BannerService {

    List<BannerDTO> getList();

    default Banner dtoToEntity(BannerDTO dto) {
        Banner banner = Banner.builder()
                .id(dto.getId())
                .imagePath(dto.getImagePath())
                .viewOrder(dto.getViewOrder())
                .build();

        return banner;
    }

    default BannerDTO entityToDTO(Banner banner) {
        BannerDTO bannerDTO = BannerDTO.builder()
                .id(banner.getId())
                .imagePath(banner.getImagePath())
                .viewOrder(banner.getViewOrder())
                .build();

        return bannerDTO;
    }
}
