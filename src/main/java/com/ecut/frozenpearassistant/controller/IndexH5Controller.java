package com.ecut.frozenpearassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
    @GetMapping("releases")
    public String releases(){
        return "releases";
    }
    @GetMapping("password")
    public String password(){
        return "password";
    }
    @GetMapping("upload")
    public String upload(){
        return "upload";
    }
    @GetMapping("release_goods")
    public String releaseGoods(){
        return "release_goods";
    }
    @GetMapping("book")
    public String book(){
        return "book";
    }
    @GetMapping("numerical")
    public String numerical(){
        return "numerical";
    }
    @GetMapping("clothes")
    public String clothes(){
        return "clothes";
    }
    @GetMapping("daily")
    public String daily(){
        return "daily";
    }
    @GetMapping("vehicle")
    public String vehicle(){
        return "vehicle";
    }
    @GetMapping("other")
    public String other(){
        return "other";
    }
    @GetMapping("after_search")
    public String afterSearch(){
        return "after_search";
    }
    @GetMapping("goods_details")
    public String goodsDetail(@RequestParam(value="productId") String productId,HttpSession session){
        session.setAttribute("productId",productId);
        return "goods_details";
    }
    @GetMapping("order_confirm")
    public String orderConfirm(@RequestParam(value="productId") String productId,HttpSession session){
        session.setAttribute("productId",productId);
        return "order_confirm";
    }
    @GetMapping("payment")
    public String payment(){
//        session.setAttribute("productId",productId);
        return "payment";
    }
    @GetMapping("pay_success")
    public String paySuccess(){
//        session.setAttribute("productId",productId);
        return "pay_success";
    }
}
