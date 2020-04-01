package com.ecut.frozenpearassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexH5Controller {
    @GetMapping("index")
    public String index(){
        return "index";
    }
    @GetMapping("register")
    public String register(){
        return "register";
    }
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @GetMapping("user_center")
    public String userCenter(){
        return "user_center";
    }
    @GetMapping("addAddress")
    public String addAddress(){
        return "addAddress";
    }
    @GetMapping("address")
    public String address(){
        return "address";
    }
    @GetMapping("orders")
    public String orders(){
        return "orders";
    }
    @GetMapping("password")
    public String password(){
        return "password";
    }
    @GetMapping("upload")
    public String upload(){
        return "upload";
    }
//    @GetMapping("user_center")
//    public String userCenter(){
//        return "user_center";
//    }
}
