package com.zipcode.slackclone.slackclone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue
    @Column(name = "MESSAGE_ID")
    private Long messageId;

    @Column(name = "CONTENT")
    private String messageContent;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    @Column(name = "FROM_USER")
    private String fromUser;

    @Column(name = "TO_CHAT")
    private Long chatId;

    public Message(){

    }

    public Message(String messageContent){
        this.messageContent = messageContent;
    }

    public Message(String fromUser, Long chatId, String messageContent) {
        this.fromUser = fromUser;
        this.chatId = chatId;
        this.messageContent = messageContent;
    }

    public Message(String fromUser, String messageContent) {
        this.fromUser = fromUser;
        this.messageContent = messageContent;
    }

    public Long getMessageId() {
        return messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getFromUser() {
        return fromUser;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date createTimeStamp(){
        return new Date();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + messageId +
                ", messageContent='" + messageContent + '\'' +
                ", timeStamp=" + timeStamp +
                ", fromUser='" + fromUser + '\'' +
                '}';
    }
}

