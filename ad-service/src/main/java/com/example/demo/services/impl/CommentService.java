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
import org.springframework.stereotype.Service;

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
    public List<CommentResponse> getAllComments()
    {
        List<Comment> comments = _commentRepository.findAll();
        return  comments.stream()
                .map(comment -> mapCommentToCommentResponse(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse getAdById(Long id) {
        Comment comment = _commentRepository.findOneById(id);
        if(comment != null) {
            return mapCommentToCommentResponse(comment);
        }
        return null;
    }

    @Override
    public CommentResponse createComment(CreateCommentRequest request) {


        Ad ad = _adRepository.findOneById(request.getAdId());

        if(request.isSimpleUser()){

            SimpleUser simpleUser = _authClient.getSimpleUser(request.getUserId());
            Comment comment = new Comment();
            comment.setComment(request.getComment());
            comment.setAd(ad);
            comment.setPublisherId(request.getUserId());
            comment.setSimpleUser(request.isSimpleUser());
            Comment savedComment = _commentRepository.save(comment);

            return mapCommentToCommentResponse(savedComment);
        } else {

            Agent agent = _authClient.getAgent(request.getUserId());
            Comment comment = new Comment();
            comment.setComment(request.getComment());
            comment.setId(request.getUserId());
            comment.setAd(ad);
            comment.setSimpleUser(request.isSimpleUser());
            Comment savedComment = _commentRepository.save(comment);

            return mapCommentToCommentResponse(savedComment);
        }
    }

    public CommentResponse mapCommentToCommentResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        AdResponse adResponse = _adService.mapAdToAdResponse(comment.getAd());
        response.setAd(adResponse);
        response.setContent(comment.getComment());
        response.setUserId(comment.getPublisherId());
        response.setSimpleUser(comment.isSimpleUser());
        return response;
    }
}
