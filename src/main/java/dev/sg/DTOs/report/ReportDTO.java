package dev.sg.DTOs.report;

import dev.sg.entities.LinkEntity;
import dev.sg.entities.ReportEntity;
import dev.sg.enums.DateRange;
import dev.sg.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ReportDTO {
    private Long id;
    private String username;
    private String body;
    private List<String> links;
    private String geotag;
    private Boolean isStatusChanged;
    private DateRange dateRange;
    private Integer categoryId;
    private Status status;

    public static ReportDTO map(ReportEntity reportEntity) {
        return ReportDTO
                .builder()
                .id(reportEntity.getId())
                .username(reportEntity.getUser().getUsername())
                .body(reportEntity.getBody())
                .links(reportEntity.getLinks().stream().map(LinkEntity::getLink).collect(Collectors.toList()))
                .geotag(reportEntity.getGeotag())
                .isStatusChanged(reportEntity.getIsStatusChanged())
                .categoryId(reportEntity.getCategoryId())
                .dateRange(DateRange.DateRangeCalculator.calculateDateRange(reportEntity.getCreatedAt()))
                .status(reportEntity.getStatus())
                .build();
    }
}
