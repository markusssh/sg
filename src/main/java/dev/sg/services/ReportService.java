package dev.sg.services;


import dev.sg.DTOs.report.ReportDTO;
import dev.sg.DTOs.report.ReportPostRequest;
import dev.sg.entities.LinkEntity;
import dev.sg.entities.ReportEntity;
import dev.sg.entities.UserEntity;
import dev.sg.enums.Status;
import dev.sg.repositories.ReportRepo;
import dev.sg.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepo reportRepo;
    private final UserRepo userRepo;
    private final LinkService linkService;

    public ReportDTO saveReport(
            ReportPostRequest request,
            String username
    ) {
        UserEntity user = userRepo.findByUsername(username).orElseThrow();

        ReportEntity report = new ReportEntity();
        reportRepo.save(report);
        List<LinkEntity> linkEntityList = linkService.saveLinks(request.getLinks(), report);

        report.setBody(request.getBody());
        report.setAddress(request.getAddress());
        report.setGeotag(request.getGeotag());
        report.setStatus(Status.NEW);
        report.setIsStatusChanged(false);
        report.setCreatedAt(LocalDateTime.now());
        report.setUser(user);
        report.setLinks(linkEntityList);
        report.setCategoryId(request.getCategoryId());

        reportRepo.save(report);

        return ReportDTO.map(report);
    }


    public List<ReportDTO> getAllByUsername(String username) {
        UserEntity user = userRepo.findByUsername(username).orElseThrow();
        Optional<List<ReportEntity>> reportEntities = reportRepo.findAllByUser(user);
        return getReportDTOS(reportEntities);
    }

    public List<ReportDTO> getAllByUsernameAndNotification(String username) {
        UserEntity user = userRepo.findByUsername(username).orElseThrow();
        Optional<List<ReportEntity>> reportEntities = reportRepo.findAllByUserAndIsStatusChangedIsTrue(user);
        return getReportDTOS(reportEntities);
    }

    private List<ReportDTO> getReportDTOS(Optional<List<ReportEntity>> reportEntities) {
        if (reportEntities.isPresent()) {
            List<ReportDTO> reportDTOS = new ArrayList<>();
            for (ReportEntity report : reportEntities.get()) {
                reportDTOS.add(ReportDTO.map(report));
            }
            return reportDTOS;
        } else {
            return new ArrayList<>();
        }
    }

    public void flagDown(Long id, String username) {
        ReportEntity report = reportRepo.findById(id).orElseThrow();
        if (Objects.equals(report.getUser().getUsername(), username)) {
            report.setIsStatusChanged(false);
            reportRepo.save(report);
        } else {
            throw new SecurityException();
        }
    }
}
