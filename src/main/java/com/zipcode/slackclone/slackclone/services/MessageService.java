package com.zipcode.slackclone.slackclone.services;

import com.zipcode.slackclone.slackclone.model.Message;
import com.zipcode.slackclone.slackclone.model.User;
import com.zipcode.slackclone.slackclone.repository.MessageRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MessageService {

    @Inject
    private MessageRepository messageRepository;

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(Long messageId){
        return messageRepository.findOne(messageId);
    }

    public Message addMessage(String fromUser, Long toChat, String messageContent){
        return addMessage(new Message(fromUser, toChat, messageContent));
    }

    public Message addMessage(String fromUser, String messageContent){
        return addMessage(new Message(fromUser, messageContent));
    }

    public Message addMessage(Message message){
       return messageRepository.save(message);
    }

    public void updateMessage(Message updatedMessage){
//        getMessageById(messageId).setMessageContent(updatedMessage);
        messageRepository.save(updatedMessage);

    }

    public void deleteMessage(Long messageId){
        messageRepository.delete(getMessageById(messageId));

    }

}
