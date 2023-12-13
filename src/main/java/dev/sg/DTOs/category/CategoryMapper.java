package dev.sg.DTOs.category;

import dev.sg.entities.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public static CategoryDTO mapDTO (CategoryEntity entity) {
        return CategoryDTO
                .builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .parentId(entity.getParentId())
                .build();
    }

    public static List<CategoryGetResponse> mapResponses(List<CategoryEntity> entities) {
        List<CategoryGetResponse> response = new ArrayList<>();
        for (CategoryEntity entity:
             entities) {
            response.add(CategoryGetResponse
                    .builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build());
        }
        return response;
    }
}
