package com.SpringUygulama.ToDo.business.impl;

import com.SpringUygulama.ToDo.business.service.CommentService;
import com.SpringUygulama.ToDo.dto.request.CommentRequestDTO;
import com.SpringUygulama.ToDo.dto.response.CommentResponseDTO;
import com.SpringUygulama.ToDo.exception.categoryException.CategoryNotFoundException;
import com.SpringUygulama.ToDo.exception.commentException.CommentNotNullException;
import com.SpringUygulama.ToDo.exception.taskException.TaskNotFoundException;
import com.SpringUygulama.ToDo.exception.userException.UserNotFoundException;
import com.SpringUygulama.ToDo.mapper.CommentMapper;
import com.SpringUygulama.ToDo.repository.CommentRepository;
import com.SpringUygulama.ToDo.repository.TaskRepository;
import com.SpringUygulama.ToDo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private static final Logger logger= LoggerFactory.getLogger(CommentServiceImpl.class);

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, TaskRepository taskRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String deleteComment(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(()->{
            logger.error("Comment id is not found");
            return new CategoryNotFoundException("Comment id is not found");
        });
        commentRepository.deleteById(commentId);
        return "Yorum silindi";
    }

    @Override
    public List<CommentResponseDTO> getAllCommentsForTask(Long taskId) {
        taskRepository.findById(taskId).orElseThrow(()->{
            logger.error("Task id is not found");
            return new TaskNotFoundException("Task id is not found");
        });
        return commentMapper.mapCommentsToCommentResponseDTOs(commentRepository.findByTask_TaskId(taskId));
    }

    @Override
    public CommentResponseDTO saveComment(Long taskId, Long userId, CommentRequestDTO commentRequestDTO) {
        taskRepository.findById(taskId).orElseThrow(()->{
            logger.error("Task id is not found");
            return new TaskNotFoundException("Task id is not found");
        });
        userRepository.findById(userId).orElseThrow(()->{
            logger.error("User id is not found");
            return new UserNotFoundException("User id is not found");
        });
        if(commentRequestDTO.getText().isBlank()){
            logger.error("Comment is null");
            throw new CommentNotNullException("Comment must be not null");
        }
        return commentMapper.mapCommentToCommentDTO(commentRepository.save(commentMapper.mapCommentRequestDTOToComment(commentRequestDTO,taskId,userId)));
    }

    @Override
    public List<CommentResponseDTO> getAllComments() {
        return commentMapper.mapCommentsToCommentResponseDTOs(commentRepository.findAll());
    }
}
