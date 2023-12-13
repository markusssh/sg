package dev.sg.repositories;

import dev.sg.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
