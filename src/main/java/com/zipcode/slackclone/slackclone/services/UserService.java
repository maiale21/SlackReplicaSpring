package com.zipcode.slackclone.slackclone.services;

import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.model.Profile;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<User> getAllUsers(){ return userRepository.findAll(); }

    public User getUserByUserName(String userName){
        return userRepository.findOne(userName);
    }

    public boolean checkUserNameExists(User userNameToCheck){
        for(User user: userRepository.findAll()){
            if(userNameToCheck.getUserName().equalsIgnoreCase(user.getUserName()))
                return true;
        }
        return false;
    }

    public User addUser(User user){
        if(checkUserNameExists(user)) return null;
            userRepository.save(user);
        return user;
    }


    public boolean updateUserEmail(String userName, String newEmail){
        User toChange = getUserByUserName(userName);
        if (checkUserNameExists(toChange)){
            toChange.setEmail(newEmail);
            return true;
        }return false;
    }

    public boolean deleteUser(String userName){
        if(checkUserNameExists(getUserByUserName(userName))) {
            userRepository.delete(userName);
            return true;
        }
        return false;
    }
}
