package com.example.system1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private final String client = "redirect:http://192.168.4.147:8080/";


    @RequestMapping("/login")
    public String login(@RequestParam(value = "islogin", required = false) String islogin, HttpServletRequest request) {
        if (islogin != null && islogin.equals("1")) {
            int f = 0;
            if (request.getCookies() != null) {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        f = 3;
                    }
                }
            }
            if (f == 3) {
                return "fuck";
            } else {
                return client + "login?service=" + request.getRequestURL();
            }
        } else {
            int k = 0;
            if (request.getCookies() != null) {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        k = 3;
                    }
                }
                if (k == 3) {
                    return client + "jiaoyan?service=" + request.getRequestURL();
                } else {
                    System.out.println("cookie不一致");
                    return client + "login?service=" + request.getRequestURL();
                }
            } else {
                System.out.println("用户没有登录，新建登录");
                String url = client + "first?service=" + request.getRequestURL();
                return url;
            }
        }

    }


}

