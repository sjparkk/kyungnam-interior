package com.kyungnam.service;


import com.kyungnam.domain.Work;
import com.kyungnam.dto.WorkDto;

import java.util.List;

public interface WorkService {

    List<WorkDto> getWorkMainList();

    List<WorkDto> getWorkAllList();

    List<WorkDto> getWorkByCategory(int category);

    WorkDto getWorkDetailById(Long id);

    /**
     * 자바8 이전에는 인터페이스는 정적 상수와 추상메소드만을 가질 수 있었지만 함수형 인터페이스 등이 도입되면서
     * 메소드에 바디까지 가진 default 메소드와 정적 메소드를 넣을 수 있다. 이 메소드들은 오버라이딩이 필요없이
     * implements 해주면 그냥 사용이 가능하다.
     */
    default Work dtoToEntity(WorkDto dto) {
        Work work = Work.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .thumnailImagePath(dto.getThumnailImagePath())
                .type(dto.getType())
                .build();

        return work;
    }

    default WorkDto entityToDTO(Work work) {
        WorkDto workDto = WorkDto.builder()
                .id(work.getId())
                .category(work.getCategory())
                .title(work.getTitle())
                .address(work.getAddress())
                .term(work.getTerm())
                .budget(work.getBudget())
                .details(work.getDetails())
                .thumnailImagePath(work.getThumnailImagePath())
                .type(work.getType())
                .content(work.getContent())
                .isMain(work.getIsMain())
                .workImages(work.getWorkImages())
                .build();

        return workDto;
    }
}
