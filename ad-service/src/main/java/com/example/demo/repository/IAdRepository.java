package com.example.demo.repository;

import com.example.demo.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdRepository extends JpaRepository<Ad, Long> {
    Ad findOneById(Long id);

    List<Ad> findAllByPublisher(Long publisher);
}
