package com.example.controller;

import com.example.entity.Task;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/api/tasks")
    public ResponseEntity<?> create(@RequestBody Task task){
        taskService.create(task);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/tasks")
    public ResponseEntity<List<Task>> findAll(){
        final List<Task> taskList = taskService.findAll();
        return taskList != null && !taskList.isEmpty()
                ? new ResponseEntity<>(taskList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<Optional<Task>> find(@PathVariable(name = "id") Long id){
        final Optional<Task> task = taskService.find(id);
        return task != null
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(name = "id") Long id, @RequestBody Task taskUpdate) {
        return taskService.find(id).map(task -> {
            task.setCreate_date(taskUpdate.getCreate_date());
            task.setChange_date(taskUpdate.getChange_date());
            task.setName(taskUpdate.getName());
            task.setDescription(taskUpdate.getDescription());
            task.setComplete_date(taskUpdate.getComplete_date());
            task.setCompleted(taskUpdate.getCompleted());
            task.setClient(taskUpdate.getClient());
            task.setCategories(taskUpdate.getCategories());
            taskService.update(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "id") Long id) {
        return taskService.find(id).map(task -> {
            taskService.delete(task);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }


}
