package com.NganTrung.ToDoList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/")
    public String index(Model model) {
        // 傳遞測試數據給模板
        model.addAttribute("title", "Thymeleaf 測試頁面");
        model.addAttribute("message", "歡迎使用 Thymeleaf 與 Spring Boot！");
        return "index"; // 對應 templates/index.html
    }
}

