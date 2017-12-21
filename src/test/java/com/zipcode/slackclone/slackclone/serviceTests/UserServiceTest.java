package com.zipcode.slackclone.slackclone.serviceTests;

import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.repository.UserRepository;
import com.zipcode.slackclone.slackclone.services.UserService;
import org.junit.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserServiceTest {

    private UserService userService;
    private User user1;
    private User user2;
    private Message message1;
    private Message message2;
    private ArrayList<User> userArray;

    @Before
    public void setUp(){
        userService = new UserService();
        user1 = new User("donald", "password1", "donald@gmail.com");
        user2 = new User("merin", "password2", "merin@gmail.com");
        message1 = new Message("merin", "hi donald");
        message2 = new Message("donald", "hi merin");
        userArray = new ArrayList<>();
    }

    @Test
    public void getAllUser(){
        userArray.add(user1);
        userArray.add(user2);
        int expected = 2;

        int actual = userArray.size();

        Assert.assertEquals(expected, actual);
    }
}
