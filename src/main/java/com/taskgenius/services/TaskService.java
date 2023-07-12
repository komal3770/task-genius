package com.taskgenius.services;

import com.taskgenius.entities.Task;
import com.taskgenius.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public void save(Task task) {
        taskRepository.save(task);
    }

    public List<Task> getTaskByUser(UUID userId) {
        return taskRepository.findByUserId(userId);
    }

    public void deleteByUser(UUID userId) {
        List<Task> tasks = getTaskByUser(userId);
        if (tasks.isEmpty())
            throw new IllegalArgumentException("No task found");
        taskRepository.deleteAll(tasks);
    }

}
