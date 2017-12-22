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

    public boolean passwordMatch(String userName, String password) {
        String password1 = userRepository.findOne(userName).getPassword();
        if (password.equals(password1)) {
            return true;
            }
            return false;
        }
    }

