package com.storeaniket.rms.controller;

import com.storeaniket.rms.dto.CategoryDTO;
import com.storeaniket.rms.model.CategoryDB;
import com.storeaniket.rms.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDB> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{categoryId}")
    public CategoryDB getCategoryById(@PathVariable(value="categoryId") Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @PostMapping("/create")
//    WHEN WE CREATE DTO WE HAVE TO WRITE DTO FILE NAME IN PARAMETER INSTEAD OF MODEL NAME
//    public String createStoreVendor(@Valid @RequestBody StoreVendor storeVendor) {
    public String createCategory(@Valid @RequestBody CategoryDTO categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable(value="id")Long id,@Valid @RequestBody CategoryDTO categoryRequest) {
        return categoryService.updateCategory(id, categoryRequest);
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable(value="categoryId") Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }


}
