package dev.sg.repositories;

import dev.sg.entities.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepo extends JpaRepository<LinkEntity, Long> {

}
