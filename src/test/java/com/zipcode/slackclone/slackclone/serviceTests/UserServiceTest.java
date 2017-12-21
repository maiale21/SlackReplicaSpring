package com.zipcode.slackclone.slackclone.serviceTests;

import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.repository.UserRepository;
import com.zipcode.slackclone.slackclone.services.UserService;
import org.junit.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserServiceTest {

    @MockBean
    private UserService userService;
    @MockBean
    private User user1;
    @MockBean
    private User user2;
    @MockBean
    private Message message1;
    @MockBean
    private Message message2;
    @MockBean
    private ArrayList<User> userArray;
    @MockBean
    private ArrayList<Message> messageArray;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp(){
        userService = new UserService();
        user1 = new User("donald", "password1", "donald@gmail.com");
        user2 = new User("merin", "password2", "merin@gmail.com");
        message1 = new Message("merin", "hi donald");
        message2 = new Message("donald", "hi merin");
        userArray = new ArrayList<>();
        userArray.add(user1);
        userArray.add(user2);
        messageArray = new ArrayList<>();
        messageArray.add(message1);
        messageArray.add(message2);
        userRepository.save(user1);
        userService.addUser(user1);
        userService.addUser(user2);
    }

    @Test
    public void getAllUser(){
        int expected = 2;

        int actual = userService.getAllUsers().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getUserByUserName(){
        String userName = "donald";
        String expected = "";

        String actual = userService.getUserByUserName(userName).toString();

        Assert.assertEquals(expected, actual);
    }
}
