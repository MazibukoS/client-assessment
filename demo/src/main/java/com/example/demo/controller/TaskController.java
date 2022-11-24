package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/users/{id}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable("id") long id, @RequestBody Task task) {
        try {
            Task _task = taskRepository
                    .save(new Task(task.getName(), task.getDescription(), id, task.getDate_time()));
            return new ResponseEntity<>(_task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{user_id}/tasks/{task_id}")
    public ResponseEntity<Task> updateTask(@PathVariable("user_id") long user_id, @PathVariable("task_id") long task_id, @RequestBody Task task) {
        Task taskData = taskRepository.findTaskByUserId(user_id,task_id);

        if (taskData != null) {
            Task _task = taskData;
            _task.setName(task.getName());
            _task.setDescription(task.getDescription());
            _task.setUser_id(task.getUser_id());
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{user_id}/tasks/{task_id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("user_id") long user_id, @PathVariable("task_id") long task_id) {
        try {
            taskRepository.deleteTaskByUserId(user_id,task_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{user_id}/tasks/{task_id}")
    public ResponseEntity<Task> getTaskInfo(@PathVariable("user_id") long user_id, @PathVariable("task_id") long task_id) {

        Task task = taskRepository.findTaskByUserId(user_id,task_id);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{user_id}/tasks")
    public ResponseEntity<List<Task>> getTasksForUser(@PathVariable("user_id") long user_id) {

        List<Task> task = taskRepository.listAllUserTasks(user_id);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
