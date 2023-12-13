package dev.sg.repositories;

import dev.sg.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {

    Optional<List<CategoryEntity>> findAllByParentId(Integer parentId);

}
