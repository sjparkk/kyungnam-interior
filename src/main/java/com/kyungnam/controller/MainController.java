package com.kyungnam.controller;

import com.kyungnam.dto.BannerDTO;
import com.kyungnam.dto.WorkDto;
import com.kyungnam.service.BannerService;
import com.kyungnam.service.WorkService;
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

    // main page
    @GetMapping("/")
    public String main(Model model) {
        List<WorkDto> workMainList = workService.getWorkMainList();
        List<BannerDTO> bannerMainList = bannerService.getList();
        model.addAttribute("workMainList", workMainList);
        model.addAttribute("bannerMainList", bannerMainList);

        return "index";
    }

}
