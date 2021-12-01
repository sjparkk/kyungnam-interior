package com.kninterior.controller;

import com.kninterior.dto.WorkDto;
import com.kninterior.service.WorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor // 아래 생성자 주입 방식 해당 어노테이션으로 대체 가능.
public class PageController { // 페이지 이동 관련 Controller
    private WorkService workService;

    @GetMapping("/work")
    public String work(Model model, @RequestParam(defaultValue = "0") int category) {
        List<WorkDto> workAllList = workService.getWorkByCategory(category);
        model.addAttribute("workAllList", workAllList);
        model.addAttribute("category", category);

        return "work";
    }

    @GetMapping("/work/detail")
    public String workDetail(Model model, @RequestParam(value = "id", required = false) Long workId) {
//        List<WorkDto> workAllList = workService.
//        model.addAttribute("workAllList", workAllList);
//        model.addAttribute("category", category);
        WorkDto workDetail = workService.getWorkDetailById(workId);
        model.addAttribute("workDetail", workDetail);

        return "work_detail";
    }


}
