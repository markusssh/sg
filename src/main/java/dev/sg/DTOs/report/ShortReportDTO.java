package dev.sg.DTOs.report;

import dev.sg.entities.ReportEntity;
import dev.sg.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortReportDTO {

    private Long id;
    private Integer categoryId;
    private Status status;

    public static ShortReportDTO map(ReportEntity report) {
        return ShortReportDTO
                .builder()
                .id(report.getId())
                .categoryId(report.getCategoryId())
                .status(report.getStatus())
                .build();
    }
}
