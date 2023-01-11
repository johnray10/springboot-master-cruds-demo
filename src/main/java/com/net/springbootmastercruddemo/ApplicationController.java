package com.net.springbootmastercruddemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping(value = "/")
    public String dashboard(){
        return "views/dashboard/index";
    }
}
