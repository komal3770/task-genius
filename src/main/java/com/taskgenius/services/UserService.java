package com.taskgenius.services;

import com.taskgenius.dto.UserDto;
import com.taskgenius.entities.User;
import com.taskgenius.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public void save(UserDto userDto){
    User user = userDto.toUser();
    userRepository.save(user);
  }

  public List<User> getAllUsers(){
    return userRepository.findAll();
  }

}
