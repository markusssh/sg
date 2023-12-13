package dev.sg.services;

import dev.sg.DTOs.report.ReportDTO;
import dev.sg.DTOs.sorting.SortingDTO;
import dev.sg.entities.CategoryEntity;
import dev.sg.entities.ReportEntity;
import dev.sg.enums.Status;
import dev.sg.repositories.CategoryRepo;
import dev.sg.repositories.ReportRepo;
import dev.sg.specifications.ReportSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeratorReportService {

    private final ReportRepo reportRepo;
    private final CategoryRepo categoryRepo;

    public List<ReportDTO> getReportsSorted(SortingDTO sortingDTO) {
        List<Integer> categoryIDs = new ArrayList<>();
        for (int id:
                sortingDTO.getParentCategoryIDs()) {
            List<CategoryEntity> categories = categoryRepo.findAllByParentId(id).orElseThrow();
            List<Integer> categoryIDsForParent = categories.stream()
                    .map(CategoryEntity::getId)
                    .toList();
            categoryIDs.addAll(categoryIDsForParent);
        }
        List<ReportEntity> reportEntities = reportRepo.findAll(ReportSpecifications.createSpecification(sortingDTO, categoryIDs));
        List<ReportDTO> reportDTOList = new ArrayList<>();
        for (ReportEntity entity:
             reportEntities) {
            reportDTOList.add(ReportDTO.map(entity));
        }
        return reportDTOList;
    }

    public ReportDTO getReportById(Long id) {
        return ReportDTO.map(reportRepo.findById(id).orElseThrow());
    }

    public void changeStatus(Long id, Status status) {
        ReportEntity report = reportRepo.findById(id).orElseThrow();
        report.setStatus(status);
        report.setIsStatusChanged(true);
        reportRepo.save(report);
    }
}
