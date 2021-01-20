package com.example.demo.services.impl;

import com.example.demo.client.AuthClient;
import com.example.demo.dto.client.Agent;
import com.example.demo.dto.client.SimpleUser;
import com.example.demo.dto.request.CreateCommentRequest;
import com.example.demo.dto.response.AdResponse;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.entity.Ad;
import com.example.demo.entity.Comment;
import com.example.demo.repository.IAdRepository;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.services.ICommentService;
import com.example.demo.util.RequestStatus;
import com.example.demo.util.ReservationStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    private final ICommentRepository _commentRepository;
    private final AuthClient _authClient;
    private final IAdRepository _adRepository;
    private final AdService _adService;

    public CommentService(ICommentRepository commentRepository, AuthClient authClient, IAdRepository adRepository, AdService adService) {
        _commentRepository = commentRepository;
        _authClient = authClient;
        _adRepository = adRepository;
        _adService = adService;
    }

    @Override
    public List<CommentResponse> getAllCommentsByAd(Long id) {
        List<Comment> filteredComments = new ArrayList<>();
        List<Comment> comments = _commentRepository.findAllByAd_Id(id);
        for(Comment c: comments){
            if(c.getStatus().equals(RequestStatus.PENDING))
                filteredComments.add(c);
        }
        return  filteredComments.stream()
                .map(comment -> mapCommentToCommentResponse(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse createComment(CreateCommentRequest request) {
        Ad ad = _adRepository.findOneById(request.getAdId());
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setAd(ad);
        comment.setStatus(RequestStatus.PENDING);
        comment.setCustomerId(request.getCustomerId());
        comment.setSimpleUser(request.isSimpleUser());
        Comment savedComment = _commentRepository.save(comment);
        return mapCommentToCommentResponse(savedComment);
    }

    @Override
    public CommentResponse approveComment(Long id) {
        Comment comment = _commentRepository.findOneById(id);
        comment.setStatus(RequestStatus.APPROVED);
        Comment savedComment = _commentRepository.save(comment);
        return mapCommentToCommentResponse(savedComment);
    }

    @Override
    public CommentResponse denyComment(Long id) {
        Comment comment = _commentRepository.findOneById(id);
        comment.setStatus(RequestStatus.DENIED);
        Comment savedComment = _commentRepository.save(comment);
        return mapCommentToCommentResponse(savedComment);
    }

    @Override
    public List<CommentResponse> getAllCommentsByStatus(String status) {
        RequestStatus requestStatus = RequestStatus.APPROVED;
        if(status.equals("PENDING")){
            requestStatus = RequestStatus.PENDING;
        }else if(status.equals("DENIED")){
            requestStatus = RequestStatus.DENIED;
        }
        List<Comment> comments = _commentRepository.findAllByStatus(requestStatus);
        return  comments.stream()
                .map(comment -> mapCommentToCommentResponse(comment))
                .collect(Collectors.toList());
    }

    public CommentResponse mapCommentToCommentResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        AdResponse adResponse = _adService.mapAdToAdResponse(comment.getAd());
        response.setAd(adResponse);
        response.setContent(comment.getContent());
        response.setStatus(comment.getStatus());
        response.setCustomerId(comment.getCustomerId());
        response.setSimpleUser(comment.isSimpleUser());
        return response;
    }
}
