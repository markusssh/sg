package dev.sg.controllers.user;

import dev.sg.DTOs.report.ReportDTO;
import dev.sg.DTOs.report.ReportPostRequest;
import dev.sg.exeptions.AppError;
import dev.sg.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("user/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;

    @PostMapping()
    public ResponseEntity<?> postReport(
            @RequestBody ReportPostRequest request,
            Principal principal
    ) {
        try {
            ReportDTO report = service.saveReport(request, principal.getName());
            return ResponseEntity.ok(report);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllReportsOfUser(Principal principal) {
        try {
            List<ReportDTO> reports = service.getAllByUsername(principal.getName());
            if (!reports.isEmpty()) {
                return ResponseEntity.ok(reports);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("notifications")
    public ResponseEntity<?> getAllNotificationsOfUser(Principal principal) {
        try {
            List<ReportDTO> reports = service.getAllByUsernameAndNotification(principal.getName());
            if (!reports.isEmpty()) {
                return ResponseEntity.ok(reports);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PatchMapping("notifications/{id}/flag_down")
    public ResponseEntity<?> flagDownNotification(
            @PathVariable Long id,
            Principal principal
    ) {
        try {
            service.flagDown(id, principal.getName());
            return ResponseEntity.ok().build();
        } catch (SecurityException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.FORBIDDEN.value(), "Couldn't reach the report"),
                    HttpStatus.FORBIDDEN
            );
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Report not found"),
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
