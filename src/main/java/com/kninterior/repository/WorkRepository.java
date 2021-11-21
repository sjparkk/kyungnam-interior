package com.kninterior.repository;

import com.kninterior.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query("select w from Work w where w.isMain = true")
    List<Work> getWorkByIsMain();

    //work 페이지 카테고리 정렬시 이용
    @Query("select w from Work w where w.category =:category")
    List<Work> getWorkByCategory(@Param("category") int category);

}
