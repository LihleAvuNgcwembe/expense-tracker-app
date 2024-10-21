package net.javaguides.expense.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.expense.dto.CategoryDto;
import net.javaguides.expense.entity.Category;
import net.javaguides.expense.mapper.CategoryMapper;
import net.javaguides.expense.repository.CategoryRepository;
import net.javaguides.expense.service.CategoryService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        // Convert CategoryDto to category entity
        Category category = CategoryMapper.mapToCategory(categoryDto);

        // Save category object into database table - categories
        Category savedCategory = categoryRepository.save(category);

        // Convert category to CategoryDto
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }
}
