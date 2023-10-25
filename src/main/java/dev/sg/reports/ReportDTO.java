package dev.sg.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReportDTO {

    private Long id;
    //private Long userId;
    private List<Long> links;
    private String title;
    private String body;
    private String address;
    private String geotag;
    private Byte status;
    private Boolean isStatusChanged;
    private LocalDateTime createdAt;

}
