package com.zipcode.slackclone.slackclone.controllerTest;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MessageController.class, secure = false)
public class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;
    private Message mockMessage = new Message("Steve", new Long(0), "Fart");
    private String messageJSON = "{\"messageId\":null,\"messageContent\":\"Fart\",\"timeStamp\":null,\"fromUser\":\"Steve\",\"chatId\":0}";

    @Before
    public void setUp(){

    }

    @Test
    public void testGetAllMessages() throws Exception {
        when(messageService.getAllMessages()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void testGetMessage() throws Exception {
        when(messageService.getMessageById(Mockito.anyLong())).thenReturn(mockMessage);
        mockMvc.perform(MockMvcRequestBuilders.get("/messages/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(messageJSON));
    }

    @Test
    public void testAddMessage() throws Exception {
        when(messageService.addMessage(Mockito.any())).thenReturn(mockMessage);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/messages/")
                .accept(MediaType.APPLICATION_JSON)
                .content(messageJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Assert.assertEquals("http://localhost/messages/", response.getHeader(HttpHeaders.LOCATION));
    }

    @Test
    public void testDeleteMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/messages/" + Mockito.anyInt())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }


}



