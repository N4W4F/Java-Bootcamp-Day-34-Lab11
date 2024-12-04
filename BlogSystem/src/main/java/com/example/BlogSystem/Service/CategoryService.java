package com.example.BlogSystem.Service;

import com.example.BlogSystem.ApiResponse.ApiException;
import com.example.BlogSystem.Model.Category;
import com.example.BlogSystem.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id, Category category) {
        Category oldCategory = categoryRepository.findCategoryById(id);
        if (oldCategory == null)
            throw new ApiException("Category with ID: " + id + " was not found");

        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findCategoryById(id);
        if (category == null)
            throw new ApiException("Category with ID: " + id + " was not found");

        categoryRepository.delete(category);
    }

    public Category getCategoryById(Integer id) {
        Category category = categoryRepository.findCategoryById(id);
        if (category == null)
            throw new ApiException("Category with ID: " + id + " was not found");

        return category;
    }

    public Category getCategoryByName(String name) {
        Category category = categoryRepository.getCategoryByName(name);
        if (category == null)
            throw new ApiException("Category with this name was not found");

        return category;
    }
}
