package com.kyungnam.repository;

import com.kyungnam.domain.WorkImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkImageRepository extends JpaRepository<WorkImage, Long> {

    @Query("select wi from WorkImage wi where wi.work.id = :work_id order by wi.imageOrder asc")
    List<WorkImage> getWorkImageByWorkId(@Param("work_id") Long id);
}
