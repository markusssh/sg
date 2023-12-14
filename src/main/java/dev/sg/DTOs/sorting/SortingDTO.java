package dev.sg.DTOs.sorting;

import dev.sg.enums.DateRange;
import dev.sg.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SortingDTO {

    private int[] parentCategoryIDs;
    private Status status;
    private DateRange dateRange;
    private boolean sortFromMinToMax;
    private int pageNum;
    private String searchQuery;
    private int pageSize;

}
