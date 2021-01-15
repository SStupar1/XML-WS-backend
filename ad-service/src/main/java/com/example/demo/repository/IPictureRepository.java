package com.example.demo.repository;

import com.example.demo.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPictureRepository extends JpaRepository<Picture, Long> {
    Picture findOneById(Long id);
}
