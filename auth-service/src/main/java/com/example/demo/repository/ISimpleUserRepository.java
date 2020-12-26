package com.example.demo.repository;

import com.example.demo.dto.response.SimpleUserResponse;
import com.example.demo.entity.SimpleUser;
import com.example.demo.entity.User;
import com.example.demo.util.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISimpleUserRepository extends JpaRepository<SimpleUser, Long> {

    SimpleUser findOneById(Long id);

    SimpleUser findOneByUser(User user);

    List<SimpleUser> findAllByRequestStatus(RequestStatus status);

    List<SimpleUser> findAllByDeleted(boolean b);
}
