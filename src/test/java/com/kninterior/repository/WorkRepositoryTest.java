package com.kninterior.repository;

import com.kninterior.domain.Work;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
    }

    @Order(2)
    @Test
    public void read() {
        Optional<Work> work = workRepository.findById(1L);
        work.ifPresent(selectWork -> {
        });
    }

    @Order(3)
    @Test
    public void update() {
        Optional<Work> work = workRepository.findById(1L);

        work.ifPresent(selectWork -> {
            selectWork.setThumnailImagePath("test2.jpg");
            selectWork.setCategory(40);
            workRepository.save(selectWork);
        });
    }

    @Order(4)
    @Test
    public void delete() {
        Optional<Work> work = workRepository.findById(1L);

        Assertions.assertTrue(work.isPresent()); // true
        work.ifPresent(selectWork -> {
            workRepository.delete(selectWork);
        });

        Optional<Work> deleteWork = workRepository.findById(1L);
        Assertions.assertFalse(deleteWork.isPresent()); // false
    }

    @Test
    @DisplayName("isMain true인 work만 반환")
    public void getWorkByIsMain_onlyTrueValuesTest() {
        Work work1 = Work.builder()
                .isMain(true)
                .build();
        workRepository.save(work1);
        Work work2 = Work.builder()
                .isMain(true)
                .build();
        workRepository.save(work2);
        Work work3 = Work.builder()
                .isMain(false)
                .build();
        workRepository.save(work3);
        Work work4 = Work.builder()
                .isMain(true)
                .build();
        workRepository.save(work4);

        List<Work> workByIsMain = workRepository.getWorkByIsMain();
        Assertions.assertEquals(3, workByIsMain.stream().filter(Work::getIsMain).count());
    }

    @Test
    @DisplayName("category 별로 select")
    public void getWorkByCategory_categorySelectTest() {
        Work work1 = Work.builder()
                .category(20)
                .build();
        workRepository.save(work1);
        Work work2 = Work.builder()
                .category(30)
                .build();
        workRepository.save(work2);
        Work work3 = Work.builder()
                .category(40)
                .build();
        workRepository.save(work3);
        Work work4 = Work.builder()
                .category(20)
                .build();
        workRepository.save(work4);

        List<Work> workByCategory = workRepository.getWorkByCategory(20);
        Assertions.assertEquals(2, workByCategory.size());
    }

}