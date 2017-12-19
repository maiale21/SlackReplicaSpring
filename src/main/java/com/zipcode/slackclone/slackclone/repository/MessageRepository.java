package com.zipcode.slackclone.slackclone.repository;

import com.zipcode.slackclone.slackclone.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
