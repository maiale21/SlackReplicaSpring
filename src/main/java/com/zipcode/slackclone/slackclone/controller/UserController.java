package com.zipcode.slackclone.slackclone.controller;

import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        logger.info("Getting all Users: ", userService.getAllUsers().toString());
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
        User toReturn = userService.getUserByUserName(userName);
        logger.info("Getting user by user name: ", toReturn.toString());
        if(toReturn.equals(null)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

//    @PutMapping("/users/{userName}")
//    public ResponseEntity<Void> updateUserEmail(@PathVariable String userName, @RequestBody String newEmail){
//        User userToUpdate = userService.getUserByUserName(userName);
//        logger.info("Update user email from: ", userToUpdate.getEmail());
//        logger.info("Updating user email to: ", newEmail);
//        if(userService.updateUserEmail(userName, newEmail)) return new ResponseEntity<>(HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        if(userService.addUser(user).equals(null)) return new ResponseEntity<>(HttpStatus.IM_USED);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userName}")
                .buildAndExpand(user.getUserName())
                .toUri();
        responseHeaders.setLocation(newUserUri);

        return new ResponseEntity<>(responseHeaders ,HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName){
        if(userService.deleteUser(userName)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}