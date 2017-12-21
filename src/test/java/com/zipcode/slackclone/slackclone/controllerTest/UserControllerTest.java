package com.zipcode.slackclone.slackclone.controllerTest;


import com.zipcode.slackclone.slackclone.controller.MessageController;
import com.zipcode.slackclone.slackclone.controller.UserController;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.zipcode.slackclone.slackclone.controller.MessageController;
import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.services.MessageService;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private User mockUser1;
    private User mockUser2;
    private User mockUser3;
    private User mockUser4;
    private String jsonMockUser1;

    @Before
    public void setUp(){
        mockUser1 = new User("donald", "password", "donald@gmail.com");
        mockUser2 = new User("merin", "password", "merin@gmail.com");
        mockUser3 = new User("ryan", "password", "ryan@gmail.com");
        mockUser4 = new User("1", "password1", "1@gmail.com");
        jsonMockUser1 = "{\"userName\":\"donald\",\"password\":\"password\",\"email\":\"donald@gmail.com\",\"profile\":{\"firstName\":null,\"lastName\":null,\"status\":null,\"phoneNumber\":null}}";

    }

    @Test
    public void getAllUsersTest() throws Exception{
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void getUserByUserName() throws Exception{
        when(userService.getUserByUserName(Mockito.anyString())).thenReturn(mockUser1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/donald")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonMockUser1));
    }

    @Test
    public void addUserTest() throws Exception{
        when(userService.addUser(Mockito.any())).thenReturn(mockUser2);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"merin\",\"password\":\"password\",\"email\":\"merin@gmail.com\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Assert.assertEquals("http://localhost/users/merin", response.getHeader(HttpHeaders.LOCATION));
    }

    @Test
    public void deleteUserTest() throws Exception{
        when(userService.deleteUser(Mockito.anyString())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/ryan")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
