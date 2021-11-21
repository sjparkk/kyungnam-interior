package com.kninterior.repository;

import com.kninterior.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    @Query("select b from Banner b where b.viewOrder < 4 ")
    List<Banner> getBannerByViewOrder();
}
