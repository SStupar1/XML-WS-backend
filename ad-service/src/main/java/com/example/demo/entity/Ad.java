package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Ad {

    public Ad() { this.creationDate = LocalDate.now(); }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agent; //id agenta ciji je oglas

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    private boolean limitedDistance;

    private int limitedKm; //ako je limitedDistance = LIMITED(true) -> na koliko je ograniceno

    private boolean cdw; //ako je true - smanjuju se troškovi u slučaju neke nezgode ili krađe automobila

    private int seats; // vroj sedista za decu

    private LocalDate creationDate; //datum kada je oglas napravljen

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    //slike
}
