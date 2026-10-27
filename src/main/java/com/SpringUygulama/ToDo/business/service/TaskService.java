package com.SpringUygulama.ToDo.business.service;

import com.SpringUygulama.ToDo.dto.request.TaskRequestDTO;
import com.SpringUygulama.ToDo.dto.response.TaskResponseDTO;

import java.util.List;

public interface TaskService {
    List<TaskResponseDTO> getAllTasks();

    TaskResponseDTO saveTask(TaskRequestDTO taskRequestDTO, Long userId);

    List<TaskResponseDTO> getAllTasksByCategoryId(Long categoryId);

    List<TaskResponseDTO> getAllTasksByUserId(Long userId);

    String deleteTask(Long id);

    TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO);
}
