package com.taskgenius.controllers;

import com.taskgenius.entities.User;
import com.taskgenius.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers(){
    return ResponseEntity.ok(userService.getAllUsers());
  }

}
