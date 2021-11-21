package com.kyungnam.restcontroller;

import com.kyungnam.dto.WorkDto;
import com.kyungnam.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkRestController {

    private final WorkService workService;

    @PostMapping("work/{selectedCategory}")
    public List<WorkDto> workCategoryList(@PathVariable("selectedCategory") int category) {
        return workService.getWorkByCategory(category);
    }

}
