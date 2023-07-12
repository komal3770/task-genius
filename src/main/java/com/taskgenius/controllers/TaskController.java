package com.taskgenius.controllers;

import com.taskgenius.dto.ApiResponse;
import com.taskgenius.dto.TaskDto;
import com.taskgenius.entities.Category;
import com.taskgenius.entities.Task;
import com.taskgenius.entities.User;
import com.taskgenius.services.CategoryService;
import com.taskgenius.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getTaskByUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> tasks = taskService.getTaskByUser(user.getId());
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks.stream().map(task ->
                        new TaskDto(task.getTitle(), task.getDescription(),
                                task.getDueDate(), task.getPriority().name(), task.getCategory().getName()))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TaskDto taskDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryService.getByName(taskDto.category());
        taskService.save(taskDto.toTask(category, user));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskService.deleteByUser(user.getId());
        return ResponseEntity.ok(new ApiResponse("Deleted all task"));
    }

}
