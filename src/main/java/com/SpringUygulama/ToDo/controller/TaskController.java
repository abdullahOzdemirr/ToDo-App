package com.SpringUygulama.ToDo.controller;

import com.SpringUygulama.ToDo.business.service.TaskService;
import com.SpringUygulama.ToDo.dto.request.TaskRequestDTO;
import com.SpringUygulama.ToDo.dto.response.TaskResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/getTasksByCategory")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasksByCategoryId(@RequestParam Long categoryId){
        return new ResponseEntity<>(taskService.getAllTasksByCategoryId(categoryId),HttpStatus.OK);
    }

    @GetMapping("/getTasksByUser")
    public  ResponseEntity<List<TaskResponseDTO>> getAllTasksByUserId(@RequestParam Long userId){
        return new ResponseEntity<>(taskService.getAllTasksByUserId(userId),HttpStatus.OK);
    }

    @PostMapping("/save/{userId}/task")
    public ResponseEntity<TaskResponseDTO> saveTaskForUser(@PathVariable Long userId,
                                                           @RequestBody TaskRequestDTO taskRequestDTO){
        return new ResponseEntity<>(taskService.saveTask(taskRequestDTO,userId),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        return new ResponseEntity<>(taskService.deleteTask(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestParam Long id, @RequestBody TaskRequestDTO taskRequestDTO){
        return new ResponseEntity<>(taskService.updateTask(id,taskRequestDTO),HttpStatus.OK);
    }
}
