package com.kyungnam.service;

import com.kyungnam.domain.Banner;
import com.kyungnam.dto.BannerDTO;
import com.kyungnam.repository.BannerRepository;
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
