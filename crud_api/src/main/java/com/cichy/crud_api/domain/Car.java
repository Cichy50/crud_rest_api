package com.cichy.crud_api.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;

    private String model;

    private String VIN;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
