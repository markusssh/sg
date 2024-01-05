package dev.sg.services;

import dev.sg.DTOs.report.ReportDTO;
import dev.sg.DTOs.sorting.SortingDTO;
import dev.sg.DTOs.user.UserDTO;
import dev.sg.entities.CategoryEntity;
import dev.sg.entities.ReportEntity;
import dev.sg.enums.Status;
import dev.sg.repositories.CategoryRepo;
import dev.sg.repositories.ReportRepo;
import dev.sg.specifications.ReportSpecifications;
import dev.sg.utils.PairOfReportsAndPageLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeratorReportService {

    private final ReportRepo reportRepo;
    private final CategoryRepo categoryRepo;

    public PairOfReportsAndPageLimit<List<ReportDTO>,Integer> getReportsSortedAndPageLimit(SortingDTO sortingDTO) {
        List<Integer> categoryIDs = new ArrayList<>();

        if (sortingDTO.getParentCategoryIDs().length!=0) {
            for (int id : sortingDTO.getParentCategoryIDs()) {
                List<CategoryEntity> categories = categoryRepo.findAllByParentId(id).orElseThrow();
                List<Integer> categoryIDsForParent = getFilteredCategoryIDs(sortingDTO, categories);
                categoryIDs.addAll(categoryIDsForParent);
            }
        } else {
            List<CategoryEntity> allCategories = categoryRepo.findAll();
            categoryIDs = getFilteredCategoryIDs(sortingDTO, allCategories);
        }

        if (categoryIDs.isEmpty()) {
            return null;
        }

        List<ReportEntity> reportEntities = reportRepo.findAll(ReportSpecifications.createSpecification(sortingDTO,
                categoryIDs));

        if (sortingDTO.isSortFromMinToMax()) {
            reportEntities.sort(Comparator.comparingLong(ReportEntity::getId));
        } else {
            reportEntities.sort(Comparator.comparingLong(ReportEntity::getId).reversed());
        }

        int startIndex = (sortingDTO.getPageNum() - 1) * sortingDTO.getPageSize();
        int endIndex = startIndex + sortingDTO.getPageSize();
        endIndex = Math.min(endIndex, reportEntities.size());
        if (startIndex > endIndex) return PairOfReportsAndPageLimit.of(new ArrayList<>(), 0); //если страница пустая
        else {
            int pageLimit = reportEntities.size() / sortingDTO.getPageSize() + 1;
            List<ReportEntity> pagedReportEntities = reportEntities.subList(startIndex, endIndex);

            List<ReportDTO> pagedReportDTOList = new ArrayList<>();
            for (ReportEntity entity : pagedReportEntities) {
                pagedReportDTOList.add(ReportDTO.map(entity));
            }

            return PairOfReportsAndPageLimit.of(pagedReportDTOList, pageLimit);

        }
    }

    private List<Integer> getFilteredCategoryIDs(SortingDTO sortingDTO, List<CategoryEntity> categories) {
        if (sortingDTO.getSearchQuery().isEmpty()) {
            return categories.stream()
                    .map(CategoryEntity::getId)
                    .toList();
        } else {
            return categories.stream()
                    .filter(
                            category -> category.getName().toLowerCase().replaceAll("(?U)[\\pP\\s]", "")
                                    .contains(sortingDTO.getSearchQuery().toLowerCase().replaceAll("(?U)[\\pP\\s]", ""))
                    )
                    .map(CategoryEntity::getId)
                    .toList();
        }
    }

    public ReportDTO getReportById(Long id) {
        return ReportDTO.map(reportRepo.findById(id).orElseThrow());
    }

    public ReportDTO changeStatus(Long id, Status status) {
        ReportEntity report = reportRepo.findById(id).orElseThrow();
        report.setStatus(status);
        report.setIsStatusChanged(true);
        return(ReportDTO.map(reportRepo.save(report)));
    }

    public UserDTO getReportUser(Long reportId) {
        ReportEntity report =  reportRepo.findById(reportId).orElseThrow();
        return UserDTO.map(report.getUser());
    }

}
