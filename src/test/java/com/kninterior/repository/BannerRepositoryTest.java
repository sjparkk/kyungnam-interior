package com.kninterior.repository;

import com.kninterior.domain.Banner;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BannerRepositoryTest {

    @Autowired
    BannerRepository bannerRepository;

    @Order(1)
    @Test
    public void create() {
        Banner banner = new Banner();
        banner.setImagePath("path.jpg");
        banner.setViewOrder(1);

        Banner newBanner = bannerRepository.save(banner);
        System.out.println("newBanner : " + newBanner);
    }

    @Order(2)
    @Test
    public void read() {
        Optional<Banner> banner = bannerRepository.findById(1L);
        //ifPresent() - Optional 객체가 감싸고 있는 값이 존재할 경우에만 실행 로직을 함수형 인자로 넘김
        banner.ifPresent(selectBanner -> {
            System.out.println("banner : " + selectBanner);
        });
    }

}