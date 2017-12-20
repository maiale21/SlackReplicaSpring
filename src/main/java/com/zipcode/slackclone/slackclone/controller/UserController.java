package com.zipcode.slackclone.slackclone.controller;

import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
        return new ResponseEntity<User>(userService.getUserByUserName(userName), HttpStatus.OK);
    }

    @PutMapping("/user/{userName}/{updatedUser}")
    public ResponseEntity<Void> updateUser(@PathVariable String userName, @PathVariable User updatedUser){
        userService.updateUser(userName, updatedUser);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/user/{userName}/{password}/{email}")
    public ResponseEntity<Void> createUser(@PathVariable String userName, @PathVariable String password, @PathVariable String email){
        userService.createUser(userName, password, email);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}