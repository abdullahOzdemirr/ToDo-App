package com.SpringUygulama.ToDo.mapper;

import com.SpringUygulama.ToDo.dto.request.CommentRequestDTO;
import com.SpringUygulama.ToDo.dto.response.CommentResponseDTO;
import com.SpringUygulama.ToDo.entity.Comment;
import com.SpringUygulama.ToDo.entity.Task;
import com.SpringUygulama.ToDo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CommentMapper {
    private final ModelMapper modelMapper;

    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CommentResponseDTO> mapCommentsToCommentResponseDTOs(List<Comment> comments) {
        List<CommentResponseDTO> commentDTOs=new ArrayList<CommentResponseDTO>();
        for(Comment comment:comments){
            CommentResponseDTO commentDTO=mapCommentToCommentDTO(comment);
            commentDTOs.add(commentDTO);
        }
        return commentDTOs;
    }

    public Comment mapCommentRequestDTOToComment(CommentRequestDTO commentRequestDTO, Long taskId, Long userId) {
        Comment comment=modelMapper.map(commentRequestDTO,Comment.class);
        User user=new User();
        user.setUserId(userId);
        comment.setUser(user);
        Task task=new Task();
        task.setTaskId(taskId);
        comment.setTask(task);
        return comment;
    }

    public CommentResponseDTO mapCommentToCommentDTO(Comment comment) {
        return modelMapper.map(comment, CommentResponseDTO.class);
    }
}
