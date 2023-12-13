package dev.sg.repositories;

import dev.sg.entities.ReportEntity;
import dev.sg.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepo extends JpaRepository<ReportEntity, Long> {
    Optional<List<ReportEntity>> findAllByUser(UserEntity user);
    Optional<List<ReportEntity>> findAllByUserAndIsStatusChangedIsTrue(UserEntity user);

    List<ReportEntity> findAll(Specification<ReportEntity> specification);
}
