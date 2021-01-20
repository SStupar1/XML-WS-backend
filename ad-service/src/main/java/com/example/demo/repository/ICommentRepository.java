package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.util.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByAd_Id(Long id);

    Comment findOneById(Long id);

    List<Comment> findAllByStatus(RequestStatus requestStatus);
}
