package dev.sg.enums;

import java.time.Duration;
import java.time.LocalDateTime;

public enum DateRange {
    THIS_DAY,
    THIS_WEEK,
    THIS_MONTH,
    THIS_YEAR,
    OVER_YEAR;

    public static class DateRangeCalculator {
        public static DateRange calculateDateRange(LocalDateTime createdAt) {
            long daysSinceCreated = Duration.between(createdAt, LocalDateTime.now()).toDays();
            if (daysSinceCreated <= 1) {
                return THIS_DAY;
            } else if (daysSinceCreated <= 7) {
                return THIS_WEEK;
            } else if (daysSinceCreated <= 31) {
                return THIS_MONTH;
            } else if (daysSinceCreated <= 366) {
                return THIS_YEAR;
            } else {
                return OVER_YEAR;
            }
        }
    }

}
