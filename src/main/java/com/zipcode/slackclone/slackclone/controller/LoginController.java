package com.zipcode.slackclone.slackclone.controller;

import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.services.LoginService;
import com.zipcode.slackclone.slackclone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

    @PostMapping ("/userLogin")
    public ResponseEntity<User> userLogin(@RequestBody User user){
        String userName = user.getUserName();
        String password = user.getPassword();
        User user1 = userService.getUserByUserName(userName);
        if(user1 == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            if(loginService.passwordMatch(userName,password)){
                return new ResponseEntity<>(user1,HttpStatus.ACCEPTED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

}
