package com.example.demo.repository;

import com.example.demo.entity.SimpleUser;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISimpleUserRepository extends JpaRepository<SimpleUser, Long> {

    SimpleUser findOneById(Long id);

    SimpleUser findOneByUser(User user);

}