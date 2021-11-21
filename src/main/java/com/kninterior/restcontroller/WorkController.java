package com.kninterior.restcontroller;

import com.kninterior.dto.WorkDto;
import com.kninterior.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @PostMapping("work/{selectedCategory}")
    public List<WorkDto> workCategoryList(@PathVariable("selectedCategory") int category) {
        return workService.getWorkByCategory(category);
    }

}
