package com.zipcode.slackclone.slackclone.controller;

import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public User userLogin(@RequestParam(value = "userName") String userName
            , @RequestParam (value = "password") String password){
        System.out.println("hello user");
        return loginService.userLogin(userName,password);
    }
    @RequestMapping("/hello")
    public String hello(){
        return loginService.hello();
    }

}
