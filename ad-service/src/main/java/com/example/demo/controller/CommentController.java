package com.example.demo.controller;

import com.example.demo.dto.request.CreateCommentRequest;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.services.ICommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentController {

    private final ICommentService _commentService;

    public CommentController(ICommentService commentService) {
        _commentService = commentService;
    }

    @GetMapping()
    public List<CommentResponse> getAllComments(){
        return _commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") Long id){

        CommentResponse commentResponse = _commentService.getAdById(id);
        if(commentResponse != null){
            return new ResponseEntity<>(commentResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Comment doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public CommentResponse createComment(@RequestBody CreateCommentRequest request){
        return _commentService.createComment(request);
    }
}
