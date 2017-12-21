package com.zipcode.slackclone.slackclone.model;

import javax.persistence.*;

@Entity
public class Profile {
    @Id
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "USER_NAME")
    private User user;

    public Profile(User user){
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", user=" + user.getUserName() +
                '}';
    }
}
