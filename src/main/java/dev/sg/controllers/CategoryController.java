package dev.sg.controllers;

import dev.sg.DTOs.category.CategoryGetResponse;
import dev.sg.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("by_parent_id/{parentId}")
    public ResponseEntity<?> getAllCategoriesByParentId(@PathVariable Integer parentId) {
        try {
            List<CategoryGetResponse> responseList = service.findAllCategoriesByParentId(parentId);
            return ResponseEntity.ok(responseList);
        } catch (IllegalArgumentException e) {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }
}
