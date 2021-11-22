package com.kninterior.repository;

import com.kninterior.domain.Banner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BannerRepositoryTest {

    @Autowired
    BannerRepository bannerRepository;

    @Test
    public void create() {
        Banner banner = new Banner();
        banner.setImagePath("path.jpg");
        banner.setViewOrder(1);

        Banner newBanner = bannerRepository.save(banner);
        System.out.println("newBanner : " + newBanner);
    }

}