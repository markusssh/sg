package dev.sg.services;


import dev.sg.DTOs.report.ReportDTO;
import dev.sg.DTOs.report.ReportPostRequest;
import dev.sg.DTOs.report.ShortReportDTO;
import dev.sg.entities.LinkEntity;
import dev.sg.entities.ReportEntity;
import dev.sg.entities.UserEntity;
import dev.sg.enums.Status;
import dev.sg.repositories.ReportRepo;
import dev.sg.repositories.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ReportService {

    private final ReportRepo reportRepo;
    private final UserRepo userRepo;

    public ReportDTO saveReport(
            ReportPostRequest request,
            String username
    ) {
        UserEntity user = userRepo.findByUsername(username).orElseThrow();
        String[] links = request.getLinks();
        ReportEntity report = new ReportEntity();

        List<LinkEntity> linkEntityList = new ArrayList<>();
        for (String link : links) {
            linkEntityList.add(LinkEntity.builder().link(link).report(report).build());
        }

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


    public List<ShortReportDTO> getAllByUsername(String username) {
        UserEntity user = userRepo.findByUsername(username).orElseThrow();
        Optional<List<ReportEntity>> reportEntities = reportRepo.findAllByUser(user);
        return getReportDTOS(reportEntities);
    }

    public List<ShortReportDTO> getAllByUsernameAndNotification(String username) {
        UserEntity user = userRepo.findByUsername(username).orElseThrow();
        Optional<List<ReportEntity>> reportEntities = reportRepo.findAllByUserAndIsStatusChangedIsTrue(user);
        return getReportDTOS(reportEntities);
    }

    private List<ShortReportDTO> getReportDTOS(Optional<List<ReportEntity>> reportEntities) {
        if (reportEntities.isPresent()) {
            List<ShortReportDTO> reportDTOs = new ArrayList<>();
            for (ReportEntity report : reportEntities.get()) {
                reportDTOs.add(ShortReportDTO.map(report));
            }
            return reportDTOs;
        } else {
            return new ArrayList<>();
        }
    }

    public ReportDTO checkReport(Long id, String username) {
        ReportEntity report = reportRepo.findById(id).orElseThrow();
        if (Objects.equals(report.getUser().getUsername(), username)) {
            report.setIsStatusChanged(false);
            return ReportDTO.map(reportRepo.save(report));
        } else {
            throw new SecurityException();
        }
    }
}
