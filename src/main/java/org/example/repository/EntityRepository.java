package org.example.repository;

import org.example.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntityRepository extends JpaRepository<Entity, Long> {
    Optional<Entity> findById(Long id);
}
