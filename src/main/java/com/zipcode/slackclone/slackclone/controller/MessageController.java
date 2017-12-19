package com.zipcode.slackclone.slackclone.controller;

import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> allMessages = messageService.getAllMessages();
        return new ResponseEntity<>(allMessages, HttpStatus.OK);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@PathVariable Integer messageId){
        Message messageById =  messageService.getMessageById(messageId);
        if (messageById == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(messageById, HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> addingMessage(@RequestBody Message newMessage){
        Message message = messageService.addMessage(newMessage);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newMessageUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(message.getId())
                .toUri();
        responseHeaders.setLocation(newMessageUri);

        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);

    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Void> deletingMessage(@PathVariable Integer messageId){
        messageService.deleteMessage(messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
