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
    private Integer id;

    @Column(name = "CONTENT")
    private String messageContent;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    @Column(name = "FROM_USER")
    private String fromUser;

    @Column(name = "TO_USER")
    private String toUser;

    public Message(){

    }

    public Message(String messageContent){
        this.messageContent = messageContent;
    }

    public Message(String fromUser, String toUser, String messageContent) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.messageContent = messageContent;
    }

    public Message(String fromUser, String messageContent) {
        this.fromUser = fromUser;
        this.messageContent = messageContent;
    }

    public Integer getId() {
        return id;
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

    public String getToUser() {
        return toUser;
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
                "id=" + id +
                ", messageContent='" + messageContent + '\'' +
                ", timeStamp=" + timeStamp +
                ", fromUser='" + fromUser + '\'' +
                '}';
    }
}

