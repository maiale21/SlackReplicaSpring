package com.zipcode.slackclone.slackclone.serviceTests;

import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.repository.UserRepository;
import com.zipcode.slackclone.slackclone.services.UserService;
import org.junit.*;

public class UserServiceTest {

    private UserService userService;
    private User user1;
    private User user2;
    private Message message1;
    private Message message2;

    @Before
    private void setUp(){
        userService = new UserService();
        user1 = new User("donald", "password1", "donald@gmail.com");
        user2 = new User("merin", "password2", "merin@gmail.com");
        message1 = new Message("merin", "hi donald");
        message2 = new Message("donald", "hi merin");
    }

    @Test
    public void
}
