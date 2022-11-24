package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskRepository extends JpaRepository<Task, Long>{

    Task findTaskByUserId(long user_id, long task_id);

    void deleteTaskByUserId(long user_id, long task_id);

    List<Task> listAllUserTasks(long user_id);
}
