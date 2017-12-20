package com.zipcode.slackclone.slackclone.controllerTest;


import com.zipcode.slackclone.slackclone.controller.MessageController;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.services.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MessageController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private User mockUser = new User("Donald", "password", "donald@gmail.com");
}
