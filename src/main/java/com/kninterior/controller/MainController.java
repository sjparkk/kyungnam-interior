package com.kninterior.controller;


import com.kninterior.dto.BannerDTO;
import com.kninterior.dto.WorkDto;
import com.kninterior.service.BannerService;
import com.kninterior.service.WorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainController {

    private final WorkService workService;
    private final BannerService bannerService;

    @GetMapping("/")
    public String main(Model model) {
        List<WorkDto> workMainList = workService.getWorkMainList();
        List<BannerDTO> bannerMainList = bannerService.getList();
        model.addAttribute("workMainList", workMainList);
        model.addAttribute("bannerMainList", bannerMainList);

        return "index";
    }
}
