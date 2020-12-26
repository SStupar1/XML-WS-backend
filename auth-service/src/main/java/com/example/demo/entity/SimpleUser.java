package com.example.demo.entity;

import com.example.demo.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUser{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String firstName;

    private String lastName;

    private String ssn;

    private String address;

    private int numOfAds;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private LocalDateTime confirmationTime;

    private boolean deleted;


}
