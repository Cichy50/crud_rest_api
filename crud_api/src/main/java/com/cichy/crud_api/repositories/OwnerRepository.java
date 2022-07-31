package com.cichy.crud_api.repositories;

import com.cichy.crud_api.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
