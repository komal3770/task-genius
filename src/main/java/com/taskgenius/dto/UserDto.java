package com.taskgenius.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.taskgenius.constants.Role;
import com.taskgenius.entities.User;
import jakarta.validation.constraints.NotNull;

@JsonInclude(Include.NON_NULL)
public record UserDto(@NotNull String name, @NotNull String email, @NotNull String password,
                      String role) {

  public User toUser() {
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(role != null ? Role.valueOf(role) : Role.USER);
    return user;
  }

}
