package dev.sg.reports;


import dev.sg.users.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepo repository;
    //private final UserRepo userRepo;
    private final LinkRepo linkRepo;

    public ReportEntity reportMapping (
                    ReportDTO reportDTO
    ) throws IllegalArgumentException {

        if (
                repository.findById(reportDTO.getId()).isEmpty() ||
                //userRepo.findById(reportDTO.getUserId()).isEmpty() ||
                linkRepo.findByReportId(reportDTO.getId()).isEmpty()
        )
            throw new IllegalArgumentException();

        List<LinkEntity> links = linkRepo.findByReportId(reportDTO.getId());

        return ReportEntity
                        .builder()
                        .id(reportDTO.getId())
                        .links(links)
                        .title(reportDTO.getTitle())
                        .body(reportDTO.getBody())
                        .address(reportDTO.getAddress())
                        .geotag(reportDTO.getGeotag())
                        .status(reportDTO.getStatus())
                        .isStatusChanged(reportDTO.getIsStatusChanged())
                        .createdAt(reportDTO.getCreatedAt())
                        .publishedAt(LocalDateTime.now())
                        .build();
    };

}
