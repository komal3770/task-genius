package com.taskgenius.repository;

import com.taskgenius.entities.Task;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

  List<Task> findByUserId(UUID userId);

}
