package com.taskgenius.dto;

import com.taskgenius.constants.Priority;
import com.taskgenius.entities.Category;
import com.taskgenius.entities.Task;
import com.taskgenius.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record TaskDto(@NotEmpty(message = "Task title cannot be empty") String title,
                      String description, @NotNull(message = "Task due-date cannot be empty") Instant dueDate, String priority,
                      @NotEmpty(message = "Category cannot be empty") String category) {

  public Task toTask(Category category, User user) {
    Task task = new Task();
    task.setTitle(title);
    task.setDescription(description);
    task.setDueDate(dueDate);
    task.setPriority(priority == null ? Priority.MEDIUM : Priority.valueOf(priority));
    task.setCategory(category);
    task.setUser(user);
    task.setCreatedAt(Instant.now());
    return task;
  }

}
