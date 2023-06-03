package com.taskgenius.controllers;

import com.taskgenius.dto.UserDto;
import com.taskgenius.entities.User;
import com.taskgenius.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers(){
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @PostMapping
  public ResponseEntity saveUser(@RequestBody UserDto userDto){
    userService.save(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
