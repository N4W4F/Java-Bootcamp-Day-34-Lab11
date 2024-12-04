package com.example.BlogSystem.Controller;

import com.example.BlogSystem.ApiResponse.ApiResponse;
import com.example.BlogSystem.Model.Category;
import com.example.BlogSystem.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        categoryService.updateCategory(id, category);
        return ResponseEntity.status(200).body(new ApiResponse("Category with ID: " + id + " has been updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse("Category with ID: " + id + " has been deleted successfully"));
    }

    @GetMapping("/get/by-id/{id}")
    public ResponseEntity getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(categoryService.getCategoryById(id));
    }

    @GetMapping("/get/by-name/{name}")
    public ResponseEntity getCategoryByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(categoryService.getCategoryByName(name));
    }
}
