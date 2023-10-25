package dev.sg.reports;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepo extends JpaRepository<LinkEntity, Long> {

    List<LinkEntity> findByReportId(Long reportId);
}
