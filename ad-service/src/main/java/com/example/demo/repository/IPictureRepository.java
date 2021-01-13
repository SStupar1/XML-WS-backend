package com.example.demo.repository;

import com.example.demo.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPictureRepository extends JpaRepository<Picture, UUID> {
    Picture findByName(String imageName);
}
