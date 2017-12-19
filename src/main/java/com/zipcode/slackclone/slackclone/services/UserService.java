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

    public List<User> getAllUsers(){
        return userRepository.findAll();

    }

    public User getUserByUserName(String userName){
        return userRepository.findOne(userName);
    }

    public void createUser(String userName, String password, String email){
        User user = new User(userName, password, email);
        userRepository.save(user);
    }

//    public void addMessage(User fromUser, String messageContent){
//        Message message = new Message(fromUser, messageContent);
//       userRepository.save(message);
//    }

    public boolean updateUser(String userName, User newUser){
        User tempUser = getUserByUserName(userName);
        if (tempUser != null){
            userRepository.save(newUser);
            return true;
        }return false;
    }

    public void deleteUser(String userName){
        userRepository.delete(userName);

    }
}
