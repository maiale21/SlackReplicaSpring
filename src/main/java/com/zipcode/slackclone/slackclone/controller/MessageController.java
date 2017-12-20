package com.zipcode.slackclone.slackclone.controller;

import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> allMessages = messageService.getAllMessages();
        logger.info("Getting all messages: {}", allMessages);
        return new ResponseEntity<>(allMessages, HttpStatus.OK);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@PathVariable Long messageId){
        Message messageById =  messageService.getMessageById(messageId);
        logger.info("Getting message by Id: {}", messageById);
        if (messageById == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(messageById, HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> addingMessage(@RequestBody Message newMessage){
        Message message = messageService.addMessage(newMessage);
        logger.info("Adding message: {}", message);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newMessageUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(message.getMessageId())
                .toUri();
        responseHeaders.setLocation(newMessageUri);

        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);

    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Void> deletingMessage(@PathVariable Long messageId){
        messageService.deleteMessage(messageId);
        logger.info("Deleting message with message Id: {}", messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/messages/{messageId}")
    public ResponseEntity<Void> updateMessage(@RequestBody Message message, @PathVariable Long messageId) {
        Message messageById = messageService.getMessageById(messageId);
        logger.info("Updating message with message Id: {}", messageId);
        logger.info("Updating message with new message: {}", messageById);
        if (messageById == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else if(messageId.equals(message.getMessageId())) {
            messageService.updateMessage(message);
            return new ResponseEntity<>(HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.CONFLICT);

    }



}
