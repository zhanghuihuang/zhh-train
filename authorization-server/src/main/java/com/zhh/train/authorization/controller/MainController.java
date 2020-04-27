package com.zhh.train.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/24 10:57 上午
 */
@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping
    public String root() {
        return "redirect:index";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("order/index")
    public String orderIndex() {
        return "order/index";
    }

    @RequestMapping("inventory/index")
    public String inventoryIndex() {
        return "inventory/index";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("401")
    public String accessDenied() {
        return "401";
    }
}
