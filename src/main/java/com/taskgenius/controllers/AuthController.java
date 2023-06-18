package com.taskgenius.controllers;

import com.taskgenius.configurations.JwtService;
import com.taskgenius.dto.JwtResponse;
import com.taskgenius.dto.LoginDto;
import com.taskgenius.dto.UserDto;
import com.taskgenius.entities.User;
import com.taskgenius.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  UserService userService;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtService jwtUtils;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginDto loginDto) {
    Authentication authentication = null;
    try {
      authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = jwtUtils.generateJwtToken(authentication);
      User userDetails = (User) authentication.getPrincipal();

      return ResponseEntity.ok(new JwtResponse(jwt,
          userDetails.getUsername()));

    } catch (BadCredentialsException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
  }

  @PostMapping("/register")
  public ResponseEntity registerUser(@RequestBody UserDto userDto){
    userService.save(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
