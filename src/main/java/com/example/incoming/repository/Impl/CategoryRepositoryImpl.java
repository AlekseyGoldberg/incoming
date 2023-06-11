package com.example.incoming.repository.Impl;

import com.example.incoming.entity.Category;
import com.example.incoming.entity.QCategory;
import com.example.incoming.repository.CategoryConnection;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImpl {
    private EntityManager entityManager;
    private CategoryConnection categoryConnection;

    @Transactional
    public List<String> getAllIncomeCategory() {
        JPAQuery<Category> query = new JPAQuery<>(entityManager);
        QCategory category = QCategory.category;
        return query.select(category.nameCategory).from(category)
                .where(category.typeCategory.eq("доход"))
                .fetch();
    }

    @Transactional
    public List<String> getAllExpenditureCategory() {
        JPAQuery<Category> query = new JPAQuery<>(entityManager);
        QCategory category = QCategory.category;
        return query.select(category.nameCategory).from(category)
                .where(category.typeCategory.eq("расход"))
                .fetch();
    }

    @Transactional
    public Category getIncomeCategory(String incomeCategoryName) {
        return categoryConnection.findByNameCategoryAndTypeCategory(incomeCategoryName, "доход");
    }

    @Transactional
    public Category getExpenditureCategory(String expenditureName) {
        return categoryConnection.findByNameCategoryAndTypeCategory(expenditureName, "расход");
    }
}
