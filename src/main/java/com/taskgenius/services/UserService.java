package com.taskgenius.services;

import com.taskgenius.dto.UserDto;
import com.taskgenius.entities.User;
import com.taskgenius.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public void save(UserDto userDto){
    User user = userDto.toUser();
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  public List<User> getAllUsers(){
    return userRepository.findAll();
  }

}
