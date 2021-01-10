package com.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin //跨域
@RequestMapping("/")
public class ViewController {
    public String index(){
        return "index.html";
    }
}

