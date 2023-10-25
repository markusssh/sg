package dev.sg.reports;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<ReportEntity, Long> {

}
