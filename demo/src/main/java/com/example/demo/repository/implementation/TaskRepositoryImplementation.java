package com.example.demo.repository.implementation;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class TaskRepositoryImplementation implements TaskRepository {

    @Override
    public Task findTaskByUserId(long user_id, long task_id) {
        //implement method
        return null;
    }

    @Override
    public void deleteTaskByUserId(long user_id, long task_id) {
      //implement method
    }

    @Override
    public List<Task> listAllUserTasks(long user_id) {
        //implement method
        return null;
    }
}
