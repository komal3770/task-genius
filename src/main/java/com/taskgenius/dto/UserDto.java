package com.taskgenius.dto;

import com.taskgenius.constants.Role;
import com.taskgenius.entities.User;
import lombok.NonNull;

public record UserDto(@NonNull String name, @NonNull String email, @NonNull String password,
                      @NonNull String role) {

  public User toUser() {
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(Role.valueOf(role));
    return user;
  }

}
