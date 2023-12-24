package com.example.springtelegrambot.model;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long> {

}
