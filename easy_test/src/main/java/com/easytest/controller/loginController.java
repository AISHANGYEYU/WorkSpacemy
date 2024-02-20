package com.easytest.controller;

import com.easytest.entity.dto.CreateImageCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class loginController{
    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type) throws IOException {
        CreateImageCode Vcode = new CreateImageCode(130, 38, 5, 10);
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-Control", "no-cache");
        response.setDateHeader("Exprires", 0);
        response.setContentType("image/jpeg");
        String code = Vcode.getCode();
        System.out.println("验证码为：" + code);
        if (type == null || type == 0) {
            session.setAttribute("check_code_key", code);
        } else {
            session.setAttribute("check_code_key_email", code);
        }
//        System.out.println(789789);
//        System.out.println(response.getOutputStream());
        Vcode.write(response.getOutputStream());
    }

    @RequestMapping("/test")
    public void getSession(HttpSession session){
        Object check_code_key = session.getAttribute("check_code_key");
        System.out.println(check_code_key.toString());
    }
}
