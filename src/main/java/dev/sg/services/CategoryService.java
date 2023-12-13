package dev.sg.services;

import dev.sg.DTOs.category.CategoryDTO;
import dev.sg.DTOs.category.CategoryMapper;
import dev.sg.DTOs.category.CategoryGetResponse;
import dev.sg.entities.CategoryEntity;
import dev.sg.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public List<CategoryGetResponse> findAllCategoriesByParentId(Integer parentId) {
        List<CategoryEntity> categoryEntitiesList
                = categoryRepo.findAllByParentId(parentId).orElseThrow(
                () -> new IllegalArgumentException(String.valueOf(parentId))
        );
        return CategoryMapper.mapResponses(categoryEntitiesList);
    }

    public CategoryDTO findById(Integer id) {
        return CategoryMapper.mapDTO(
                categoryRepo.findById(id)
                    .orElseThrow(
                            () -> new IllegalArgumentException(String.valueOf(id))
                    )
        );
    }

    /*public CategoryEntity saveCategory(CategoryDTO categoryDTO) {
        return (categoryRepo.save(
                CategoryEntity
                        .builder()
                            .id(categoryDTO.getId())
                            .name(categoryDTO.getName())
                            .parentId(categoryDTO.getParentId())
                        .build()
            )
        );
    }

    public void deleteCategory(Long id) {
        categoryRepo.delete(
                categoryRepo.findById(id).orElseThrow(
                        () -> new IllegalArgumentException(String.valueOf(id))
                )
        );
    }*/
}
