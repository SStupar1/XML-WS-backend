package com.example.demo.services;

import com.example.demo.dto.request.CreateCommentRequest;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.entity.Comment;

import java.util.List;

public interface ICommentService {
     List<CommentResponse> getAllCommentsByAd(Long id);
     
     CommentResponse createComment(CreateCommentRequest request);

     CommentResponse approveComment(Long id);

     CommentResponse denyComment(Long id);

     List<CommentResponse> getAllCommentsByStatus(String status);
}
