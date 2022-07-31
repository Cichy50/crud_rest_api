package com.cichy.crud_api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER,mappedBy = "owner")
    private Set<Car> cars;
}
