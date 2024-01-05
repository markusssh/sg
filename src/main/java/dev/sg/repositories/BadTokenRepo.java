package dev.sg.repositories;

import dev.sg.entities.BadTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BadTokenRepo extends JpaRepository<BadTokenEntity, Long> {
    Optional<BadTokenEntity> findBadTokenEntityByToken(String token);
}
