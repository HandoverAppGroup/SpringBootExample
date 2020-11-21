package com.example.handoverapp.controller;

import com.example.handoverapp.entity.Task;
import com.example.handoverapp.exception.ResourceNotFoundException;
import com.example.handoverapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        task.getStatus().setDateCreated(new java.util.Date());
        return taskRepository.save(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long taskId) throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task was not found for this id " + taskId));
        return ResponseEntity.ok().body(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTaskDetails(@PathVariable(value = "id") Long taskId,
                                                   @Valid @RequestBody Task newTask) throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));
        task.setPatient(newTask.getPatient());
        task.setDetails(newTask.getDetails());
        task.setStatus(newTask.getStatus());
        task.setCompleter(newTask.getCompleter());
        task.setCreator(newTask.getCreator());
        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public Map<String, Boolean> deleteTask(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

        taskRepository.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
