package dev.sg.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;
    private final ReportRepo repository;

    @PostMapping
    public ResponseEntity<?> createEmptyReport() {
        ReportEntity reportEntity = ReportEntity.builder().createdAt(LocalDateTime.now()).build();
        return ResponseEntity.ok(repository.save(reportEntity));
    }

    @PutMapping
    public ResponseEntity<?> publishReport(
            @RequestBody ReportDTO reportDTO
    ) {
        try {
            ReportEntity reportEntity = service.reportMapping(reportDTO);
            return ResponseEntity.ok(reportEntity);
        }
        catch (IllegalArgumentException e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> changeStatus(
            @RequestParam Long id, byte status
    ) {
        if (repository.findById(id).isEmpty()) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        } else {
            ReportEntity report = repository.findById(id).get();
            report.setStatus(status);
            report.setIsStatusChanged(true);
            return ResponseEntity.ok(repository.save(report));
        }
    }

    @PutMapping
    public ResponseEntity<?> flagDownStatus(
            @RequestParam Long id
    ) {
        if (repository.findById(id).isEmpty()) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        } else {
            ReportEntity report = repository.findById(id).get();
            report.setIsStatusChanged(false);
            return ResponseEntity.ok(repository.save(report));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllReports() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSingleReport(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id));
    }
}
