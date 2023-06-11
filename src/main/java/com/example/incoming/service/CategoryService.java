package com.example.incoming.service;

import com.example.incoming.repository.Impl.CategoryRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepositoryImpl categoryRepository;
    private AuthService authService;

    public List<String> getAllIncomeCategory(String jwt) {
        authService.checkJwt(jwt);
        return categoryRepository.getAllIncomeCategory();
    }

    public List<String> getAllExpenditureCategory(String jwt) {
        authService.checkJwt(jwt);
        return categoryRepository.getAllExpenditureCategory();
    }

}
