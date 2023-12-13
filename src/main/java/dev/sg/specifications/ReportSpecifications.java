package dev.sg.specifications;

import dev.sg.DTOs.sorting.SortingDTO;
import dev.sg.entities.ReportEntity;
import dev.sg.enums.DateRange;
import dev.sg.enums.Status;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportSpecifications {

    public static Specification<ReportEntity> createSpecification(SortingDTO sortingDTO, List<Integer> categoryIDs) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (sortingDTO.getParentCategoryIDs() != null && sortingDTO.getParentCategoryIDs().length > 0) {
                predicates.add(root.get("categoryId").in(categoryIDs));
            }

            if (sortingDTO.getStatus() != null) {
                if (sortingDTO.getStatus() == Status.RESOLVED) {
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.equal(root.get("status"), Status.DENIED),
                            criteriaBuilder.equal(root.get("status"), Status.APPROVED)
                    ));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get("status"), sortingDTO.getStatus()));
                }
            }

            if (sortingDTO.getDateRange() != null) {
                DateRange dateRange = sortingDTO.getDateRange();
                Path<LocalDateTime> createdAt = root.get("createdAt");

                switch (dateRange) {
                    case THIS_DAY:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(createdAt, LocalDateTime.now().minusDays(1)));
                        break;
                    case THIS_WEEK:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(createdAt, LocalDateTime.now().minusWeeks(1)));
                        break;
                    case THIS_MONTH:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(createdAt, LocalDateTime.now().minusMonths(1)));
                        break;
                    case THIS_YEAR:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(createdAt, LocalDateTime.now().minusYears(1)));
                        break;
                    case OVER_YEAR:
                        predicates.add(criteriaBuilder.lessThan(createdAt, LocalDateTime.now().minusYears(1)));
                        break;
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
