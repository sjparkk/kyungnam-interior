package com.kyungnam.service;


import com.kyungnam.domain.Banner;
import com.kyungnam.dto.BannerDTO;

import java.util.List;

public interface BannerService {

    List<BannerDTO> getList();

    default Banner dtoToEntity(BannerDTO dto) {

        return Banner.builder()
                .id(dto.getId())
                .imagePath(dto.getImagePath())
                .viewOrder(dto.getViewOrder())
                .build();
    }

    default BannerDTO entityToDTO(Banner banner) {

        return BannerDTO.builder()
                .id(banner.getId())
                .imagePath(banner.getImagePath())
                .viewOrder(banner.getViewOrder())
                .build();
    }
}
