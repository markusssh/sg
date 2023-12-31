package dev.sg.controllers.moderator;

import dev.sg.DTOs.report.ReportDTO;
import dev.sg.DTOs.sorting.SortingDTO;
import dev.sg.DTOs.user.UserDTO;
import dev.sg.enums.Status;
import dev.sg.exeptions.AppError;
import dev.sg.services.ModeratorReportService;
import dev.sg.utils.PairOfReportsAndPageLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("moderator/report")
@RequiredArgsConstructor
public class ModeratorReportController {

    private final ModeratorReportService moderatorReportService;

    @GetMapping("user/{reportId}")
    public ResponseEntity<?> getReportUser(@PathVariable Long reportId) {
        try {
            UserDTO userDTO =  moderatorReportService.getReportUser(reportId);
            return ResponseEntity.ok(userDTO);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Report not found"),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping
    public ResponseEntity<?> getReportsSorted(@RequestBody SortingDTO sortingDTO) {
        try {
            PairOfReportsAndPageLimit<List<ReportDTO>,Integer> reportsSortedAndPageLimit = moderatorReportService.getReportsSortedAndPageLimit(sortingDTO);
            if (reportsSortedAndPageLimit == null || reportsSortedAndPageLimit.getReport().isEmpty()) {
                return new ResponseEntity<>(
                        new AppError(HttpStatus.NOT_FOUND.value(), "Reports not found"),
                        HttpStatus.NOT_FOUND
                );
            } else {
                return ResponseEntity
                        .ok()
                        .body(reportsSortedAndPageLimit);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Parent category not found"),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getReportById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(moderatorReportService.getReportById(id));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Report not found"),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PatchMapping("status/{id}")
    public ResponseEntity<?> changeStatus(
            @PathVariable Long id,
            @RequestParam Status status
    ) {
        try {
            return ResponseEntity.ok(moderatorReportService.changeStatus(id, status));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Report not found"),
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
