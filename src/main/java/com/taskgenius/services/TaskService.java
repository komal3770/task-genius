package com.taskgenius.services;

import com.taskgenius.entities.Task;
import com.taskgenius.repository.TaskRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

  @Autowired
  TaskRepository taskRepository;

  @Transactional
  public ResponseEntity<?> save(Task task){
    taskRepository.save(task);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  public List<Task> getTaskByUser(UUID userId){
    return taskRepository.findByUserId(userId);
  }

}
