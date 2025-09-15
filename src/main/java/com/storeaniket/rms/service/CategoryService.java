package com.storeaniket.rms.service;

import com.storeaniket.rms.dto.CategoryDTO;
import com.storeaniket.rms.model.CategoryDB;

import java.util.List;

public interface CategoryService {

    public String createCategory(CategoryDTO categoryRequest);
    public String updateCategory(Long id,CategoryDTO categoryRequest);
    public String deleteCategory(Long categoryId);
    public CategoryDB getCategory(Long categoryId);
    public List<CategoryDB> getAllCategory();

}
