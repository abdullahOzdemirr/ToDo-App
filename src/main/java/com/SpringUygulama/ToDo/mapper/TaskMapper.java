package com.SpringUygulama.ToDo.mapper;

import com.SpringUygulama.ToDo.dto.request.TaskRequestDTO;
import com.SpringUygulama.ToDo.dto.response.TaskResponseDTO;
import com.SpringUygulama.ToDo.entity.Category;
import com.SpringUygulama.ToDo.entity.Task;
import com.SpringUygulama.ToDo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TaskMapper {
    private final ModelMapper modelMapper;

    public TaskMapper() {
        this.modelMapper = new ModelMapper();
    }

    public List<TaskResponseDTO> mapTasksToTaskDTOs(List<Task> tasks) {
        List<TaskResponseDTO> taskDTOs=new ArrayList<TaskResponseDTO>();
        for(Task task:tasks){
            TaskResponseDTO taskDTO=mapTaskToTaskDTO(task);
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }
    public TaskResponseDTO mapTaskToTaskDTO(Task task){
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    public Task mapTaskRequestDTOToTask(TaskRequestDTO taskRequestDTO, Long userId) {
        Task task=modelMapper.map(new TaskResponseDTO(taskRequestDTO.getTitle(),taskRequestDTO.getDescription(),taskRequestDTO.isCompleted()),Task.class);
        Category category=new Category();
        category.setCategoryId(taskRequestDTO.getCategoryId());
        task.setCategory(category);
        User user=new User();
        user.setUserId(userId);
        task.setUser(user);
        return task;
    }
}
