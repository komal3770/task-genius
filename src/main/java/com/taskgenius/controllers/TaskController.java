package com.taskgenius.controllers;

import com.taskgenius.dto.TaskDto;
import com.taskgenius.entities.Category;
import com.taskgenius.entities.Task;
import com.taskgenius.entities.User;
import com.taskgenius.services.CategoryService;
import com.taskgenius.services.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

  @Autowired
  TaskService taskService;

  @Autowired
  CategoryService categoryService;

  @GetMapping
  public List<Task> getTaskByUserId(){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return taskService.getTaskByUser(user.getId());
  }

  @PostMapping
  public void save(@Valid @RequestBody TaskDto taskDto){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Category category = categoryService.getByName(taskDto.category());
    taskService.save(taskDto.toTask(category, user));
  }

}
