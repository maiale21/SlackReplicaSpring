package com.zipcode.slackclone.slackclone.controllerTest;


import com.zipcode.slackclone.slackclone.controller.LoginController;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.services.LoginService;
import com.zipcode.slackclone.slackclone.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class, secure = false)
public class LoginControllerTest {
    ArrayList<User> userArrayList = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private UserService userService;

    User user1;


    @Before
    public void setUp(){
        user1 = new User("user1","password1");
        User user2 = new User("user2","password2");
        User user3 = new User("user3","password3");
        userArrayList.add(user1);
        userArrayList.add(user2);
        userArrayList.add(user3);
    }
    String mockUser = "{\"userName\":\"user1\",\"password\":\"password1\",\"email\":null,\"profile\":null}";

    @Test
    public void userLoginTest()throws Exception{

        when(userService.getUserByUserName(Mockito.anyString())).thenReturn(user1);
        when(loginService.passwordMatch(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/userLogin")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"user1\",\"password\":\"password1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.ACCEPTED.value()))
                .andExpect(content().string(mockUser));
    }

}
