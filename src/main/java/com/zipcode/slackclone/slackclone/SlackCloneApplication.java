package com.zipcode.slackclone.slackclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SlackCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlackCloneApplication.class, args);
	}
}
