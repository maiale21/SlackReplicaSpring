package com.zipcode.slackclone.slackclone.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne
    private Profile profile;

    public User(){

    }

    public User(String username,String password){
        this.userName = username;
        this.password = password;
    }

    public User(String userName, String password, String email){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.profile = new Profile();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", profile=" + profile +
                '}';
    }


}
