package com.example.IssueTrackingSystem.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @GetMapping("/")   // 첫번째 로컬호스트 8080으로 들어오면 이것이 호출됨
    public String index() {
        return "index";
    }

    @RequestMapping("/project")
    public String project() {
        return "project";
    }

    @RequestMapping("/issue")
    public String issue() {
        return "issue";
    }
}
