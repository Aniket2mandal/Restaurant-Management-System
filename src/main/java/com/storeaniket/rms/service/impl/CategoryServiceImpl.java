package com.storeaniket.rms.service.impl;

import com.storeaniket.rms.dto.CategoryDTO;
import com.storeaniket.rms.model.CategoryDB;
import com.storeaniket.rms.repository.CategoryRepository;
import com.storeaniket.rms.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String createCategory(CategoryDTO catedto) {

        CategoryDB category = new CategoryDB();
        category.setId(catedto.getId());
        category.setName(catedto.getName());

        categoryRepository.save(category);
        return "success";
    }

    @Override
    public String updateCategory(Long id, CategoryDTO catedto) {
        CategoryDB category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("category not found"));
//        category.setId(catedto.getId());
        category.setName(catedto.getName());

        categoryRepository.save(category);
        return "success";
    }

    public String deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return "Success";
    }

    public CategoryDB getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    public List<CategoryDB> getAllCategory() {
        return categoryRepository.findAll();
    }
}
