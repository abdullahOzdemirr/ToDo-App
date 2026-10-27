package com.SpringUygulama.ToDo.controller;

import com.SpringUygulama.ToDo.business.service.CommentService;
import com.SpringUygulama.ToDo.dto.request.CommentRequestDTO;
import com.SpringUygulama.ToDo.dto.response.CommentResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAll/{taskId}")
    public ResponseEntity<List<CommentResponseDTO>> getAllCommentForTask(@RequestParam Long taskId){
        return new ResponseEntity<>(commentService.getAllCommentsForTask(taskId), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CommentResponseDTO>> getAllComment(){
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @PostMapping("/save/{taskId}/comments")
    public ResponseEntity<CommentResponseDTO> saveComment(@PathVariable Long taskId,
                                                          @RequestParam Long userId,
                                                          @RequestBody CommentRequestDTO commentRequestDTO){
        return new ResponseEntity<>(commentService.saveComment(taskId,userId,commentRequestDTO),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@RequestParam Long commentId){
        return new ResponseEntity<>(commentService.deleteComment(commentId),HttpStatus.OK);
    }
}
