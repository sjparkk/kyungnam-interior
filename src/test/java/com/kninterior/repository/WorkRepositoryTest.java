package com.kninterior.repository;

import com.kninterior.domain.Work;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class WorkRepositoryTest {

    @Autowired
    WorkRepository workRepository;

    @Order(1)
    @Test
    public void create() {
        Work work = new Work(20,"홍은동","test.jpg","빌라","빌라입니다.");
        Work newWork = workRepository.save(work);
        log.info("newWork : " + newWork);
    }
}