package com.example.BlogSystem.Repository;

import com.example.BlogSystem.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryById(Integer id);

    @Query("select c from Category c where c.name = ?1")
    Category getCategoryByName(String name);
}
