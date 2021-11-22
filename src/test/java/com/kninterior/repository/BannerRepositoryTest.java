package com.kninterior.repository;

import com.kninterior.domain.Banner;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

    @Order(3)
    @Test
    public void update() {
        Optional<Banner> banner = bannerRepository.findById(1L);

        banner.ifPresent(selectBanner -> {
            selectBanner.setViewOrder(2);
            selectBanner.setImagePath("path2.jpg");
            Banner newBanner = bannerRepository.save(selectBanner);
            System.out.println("update banner : " + newBanner);
        });
    }

    @Order(4)
    @Test
    public void delete() {
        Optional<Banner> banner = bannerRepository.findById(1L);

        Assertions.assertTrue(banner.isPresent()); // true
        banner.ifPresent(selectBanner -> {
            bannerRepository.delete(selectBanner);
        });

        Optional<Banner> deleteBanner = bannerRepository.findById(1L);
        Assertions.assertFalse(deleteBanner.isPresent()); // false
    }

    @Test
    @DisplayName("반환된 banner 개수가 4미만이여야 함(viewOrder 1,2,3만 리스팅 )")
    public void getBannerByViewOrder_bannerLessThan4Test() {
        List<Banner> bannerByViewOrder = bannerRepository.getBannerByViewOrder();

        Assertions.assertTrue(bannerByViewOrder.size() < 4);
    }
}