package com.zipcode.slackclone.slackclone.services;

import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class LoginService {
    @Inject
    UserRepository userRepository;

    public User userLogin(String userName, String password){
        String userName1;
        String password1;
        userName1 = userRepository.findOne(userName).getUserName();
        password1 = userRepository.findOne(password).getPassword();
        System.out.println("2nd test");
        if(userName.equals(userName1)){
            if(password.equals(password1)){
                return userRepository.getOne(userName);
            }
            else {
                userRepository.findOne(userName).setPassword("abcABC");
                return userRepository.getOne(userName);
            }
        }
        else{
            return null;
        }
    }
    public String hello(){
        return "Hello";
    }
}
