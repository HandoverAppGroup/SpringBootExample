package com.example.handoverapp.controller;

import com.example.handoverapp.dto.TaskDTO;
import com.example.handoverapp.entity.Task;
import com.example.handoverapp.exception.TaskNotFoundException;
import com.example.handoverapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/api")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    List<Task> all() {
        return taskService.getAll();
    }

    @GetMapping("/tasks/recent")
    List<Task> recent() {
        return taskService.getRecentOrUncompletedTasks();
    }

    @GetMapping("/tasks/uncompleted")
    List<Task> uncompleted() {
        return taskService.getUncompletedTasks();
    }

    @GetMapping("/tasks/sinceDate/{date}")
    List<Task> sinceDate(@PathVariable("date") String date) {
        return taskService.getTasksSince(date);
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getById(@PathVariable("id") Long id) {
        Task task = taskService.getById(id).orElseThrow(() -> new TaskNotFoundException("No task found for this id"));
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/tasks")
    ResponseEntity<?> createTask(@RequestBody TaskDTO tdto) {
        Task created = taskService.create(tdto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/tasks/{id}")
    ResponseEntity<Task> update(@PathVariable("id") Long id, @RequestBody TaskDTO tdto) {
        Task task = taskService.update(tdto, id).orElseThrow(() -> new TaskNotFoundException("No task found for this id"));
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        Task task = taskService.getById(id).orElseThrow(() -> new TaskNotFoundException("No task found for this id"));
        taskService.delete(task.getId());
        return ResponseEntity.status(204).build();
    }
}
