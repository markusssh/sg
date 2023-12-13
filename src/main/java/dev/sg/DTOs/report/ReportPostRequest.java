package dev.sg.DTOs.report;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportPostRequest {

    private String body;
    private String address;
    private String geotag;
    private Integer categoryId;
    private String[] links;

}
