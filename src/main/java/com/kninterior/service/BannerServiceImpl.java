package com.kninterior.service;

import com.kninterior.domain.Banner;
import com.kninterior.dto.BannerDTO;
import com.kninterior.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService{

        private final BannerRepository bannerRepository;


        @Override
        public List<BannerDTO> getList() {

                List<Banner> bannerList = bannerRepository.getBannerByViewOrder();

                return bannerList.stream().map(banner -> entityToDTO(banner))
                        .collect(Collectors.toList());
        }
}
