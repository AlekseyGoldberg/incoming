package com.example.incoming.repository;

import com.example.incoming.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryConnection extends JpaRepository<Category, Long> {
    Category findByNameCategoryAndTypeCategory(String nameCategory, String typeCategory);
}
