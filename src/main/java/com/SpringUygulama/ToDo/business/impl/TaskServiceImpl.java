package com.SpringUygulama.ToDo.business.impl;

import com.SpringUygulama.ToDo.business.service.TaskService;
import com.SpringUygulama.ToDo.dto.request.TaskRequestDTO;
import com.SpringUygulama.ToDo.dto.response.TaskResponseDTO;
import com.SpringUygulama.ToDo.entity.Task;
import com.SpringUygulama.ToDo.exception.categoryException.CategoryNotFoundException;
import com.SpringUygulama.ToDo.exception.taskException.TaskNotFoundException;
import com.SpringUygulama.ToDo.exception.taskException.TaskNotNullException;
import com.SpringUygulama.ToDo.exception.userException.UserNotFoundException;
import com.SpringUygulama.ToDo.mapper.TaskMapper;
import com.SpringUygulama.ToDo.repository.CategoryRepository;
import com.SpringUygulama.ToDo.repository.TaskRepository;
import com.SpringUygulama.ToDo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private static final Logger logger= LoggerFactory.getLogger(TaskServiceImpl.class);

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
    public boolean isEmptyCategory(Long id){
        categoryRepository.findById(id).orElseThrow(()->{
            logger.error("Category id is not found");
            return new CategoryNotFoundException("Category id is not found");
        });
        return true;
    }
    public boolean isEmptyUser(Long id){
        userRepository.findById(id).orElseThrow(()->{
            logger.error("User id is not found");
            return new UserNotFoundException("User id is not found");
        });
        return true;
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        return taskMapper.mapTasksToTaskDTOs(taskRepository.findAll());
    }

    @Override
    public TaskResponseDTO saveTask(TaskRequestDTO taskRequestDTO, Long userId) {
        if(taskRequestDTO.getTitle().isBlank()||taskRequestDTO.getDescription().isBlank()){
            logger.error("Task is null");
            throw new TaskNotNullException("Task must be not null");
        }
        if(!isEmptyUser(userId))return null;
        if(!isEmptyCategory(taskRequestDTO.getCategoryId()))return null;
        return taskMapper.mapTaskToTaskDTO(taskRepository.save(taskMapper.mapTaskRequestDTOToTask(taskRequestDTO,userId)));
    }

    @Override
    public List<TaskResponseDTO> getAllTasksByCategoryId(Long categoryId) {
        if(!isEmptyCategory(categoryId))return null;
        return taskMapper.mapTasksToTaskDTOs(taskRepository.findByCategory_CategoryId(categoryId));
    }

    @Override
    public List<TaskResponseDTO> getAllTasksByUserId(Long userId) {
        if(!isEmptyUser(userId))return null;
        return taskMapper.mapTasksToTaskDTOs(taskRepository.findByUser_UserId(userId));
    }

    @Override
    public String deleteTask(Long id) {
        taskRepository.findById(id).orElseThrow(()->{
            logger.error("Task id is not found");
            return new TaskNotFoundException("Task id is not found");
        });
        taskRepository.deleteById(id);
        return "Görev silindi";
    }

    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {//optimize edilebilir
        Task task=taskRepository.findById(id).orElseThrow(()->{
            logger.error("Task is not found");
            return new TaskNotFoundException("Task is not found exception");
        });
        if(!isEmptyCategory(taskRequestDTO.getCategoryId()))return null;
        Task t2=taskMapper.mapTaskRequestDTOToTask(taskRequestDTO,task.getUser().getUserId());
        t2.setTaskId(id);
        return taskMapper.mapTaskToTaskDTO(taskRepository.save(t2));
    }
}
