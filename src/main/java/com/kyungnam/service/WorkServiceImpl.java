package com.kyungnam.service;

import com.kyungnam.domain.Work;
import com.kyungnam.domain.WorkImage;
import com.kyungnam.dto.WorkDto;
import com.kyungnam.repository.WorkImageRepository;
import com.kyungnam.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkServiceImpl implements WorkService{

    private final WorkRepository workRepository;
    private final WorkImageRepository workImageRepository;

    @Override
    public List<WorkDto> getWorkMainList() {
        List<Work> workList = workRepository.getWorkByIsMain();

        return workList.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkDto> getWorkAllList() {
        List<Work> workAllList = workRepository.findAll();
        return workAllList.stream()
                .map( work -> entityToDTO(work))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkDto> getWorkByCategory(int category) {

        List<Work> workByCategory = new ArrayList<>();

        if(category == 0) {
            workByCategory = workRepository.findAll();
        } else {
            workByCategory = workRepository.getWorkByCategory(category);
        }

        return  workByCategory.stream()
                .map(work -> entityToDTO(work))
                .collect(Collectors.toList());
    }

    @Override
    public WorkDto getWorkDetailById(Long id) {
        List<WorkImage> workImages = workImageRepository.getWorkImageByWorkId(id);
        Work work = workRepository.findById(id).get();
        work.setWorkImages(workImages); // 추후 메소드 만들것.
        return entityToDTO(work);
    }
}
