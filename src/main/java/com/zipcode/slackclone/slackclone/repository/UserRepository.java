package com.zipcode.slackclone.slackclone.repository;

import com.zipcode.slackclone.slackclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
