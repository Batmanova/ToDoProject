package com.example.service;

import com.example.entity.Task;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void create(Task task){
        taskRepository.save(task);
    }

    public void update(Task task){
        taskRepository.save(task);
    }

    public void delete(Task task){
        taskRepository.delete(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Optional<Task> find(Long id){
        return taskRepository.findById(id);
    }

}
