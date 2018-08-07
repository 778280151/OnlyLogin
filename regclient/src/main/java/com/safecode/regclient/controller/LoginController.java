package com.safecode.regclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {
    private String cc;
    private String first;

    @RequestMapping("/first")
    public String first(HttpServletRequest request, String service) {
        first = service;
        System.out.println(first);
        if (request.getCookies() == null) {
            return "first";
        } else {
            return "不是第一次登录";
        }
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, String name, String pwd, String service) {
        System.out.println("传进来的网址" + first);
//        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();

        if (name != null && pwd != null) {
            if (name.equals("zcj") && pwd.equals("123456")) {
                System.out.println("firstlogin");
                String token = UUID.randomUUID().toString();
                cc = token;
                System.out.println("cc的值" + cc);
                Cookie cookie = new Cookie("user", token);
                response.addCookie(cookie);
                return "redirect:" + first + "?islogin=1";
            } else {
                return "forward:/first";
            }
        } else {
            return "forward:/first";
        }
    }

    @RequestMapping("/jiaoyan")
    public String jiaoyan(HttpServletRequest request, String service) {
        System.out.println("cc 的值" + cc);
        Cookie[] cookies = request.getCookies();
        System.out.println("进入校验");
        int k = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getValue().equals(cc)) {
                k = 3;
                break;
            }
        }
        if (k == 3) {
            return "redirect:" + service + "?islogin=1";
        } else {
            return "不一致";
        }
    }

}
