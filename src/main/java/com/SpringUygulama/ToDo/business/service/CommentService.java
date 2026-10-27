package com.SpringUygulama.ToDo.business.service;

import com.SpringUygulama.ToDo.dto.request.CommentRequestDTO;
import com.SpringUygulama.ToDo.dto.response.CommentResponseDTO;

import java.util.List;

public interface CommentService {
    String deleteComment(Long commentId);

    List<CommentResponseDTO> getAllCommentsForTask(Long taskId);

    CommentResponseDTO saveComment(Long taskId, Long userId, CommentRequestDTO commentRequestDTO);

    List<CommentResponseDTO> getAllComments();
}
