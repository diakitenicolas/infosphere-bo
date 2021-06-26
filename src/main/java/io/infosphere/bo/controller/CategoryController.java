package io.infosphere.bo.controller;

import io.infosphere.bo.dto.CategoryDto;
import io.infosphere.bo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public void create(@RequestBody CategoryDto categoryDto) {

        categoryService.create(categoryDto);

    }




}
