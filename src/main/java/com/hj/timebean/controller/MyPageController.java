package com.hj.timebean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyPageController {

    @GetMapping("/myPage")
    public String userPage(){
        return "userPage/myPage";
    }


}
