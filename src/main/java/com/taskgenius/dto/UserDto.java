package com.taskgenius.dto;

import com.taskgenius.entities.User;

public record UserDto(String name, String email, String password) {

  public User toUser(){
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    return user;
  }

}
