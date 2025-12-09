package com.quanlydiem.controllers;

import com.quanlydiem.services.HocSinhService;
import com.quanlydiem.services.DiemMonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @Autowired
    private HocSinhService hocSinhService;

    @Autowired
    private DiemMonHocService diemMonHocService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("totalStudents", hocSinhService.getAllHocSinh().size());
        model.addAttribute("stats", hocSinhService.getThongKe());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("topStudents", hocSinhService.getTop5Students());
        return "dashboard";
    }
}
