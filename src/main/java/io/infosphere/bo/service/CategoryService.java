package io.infosphere.bo.service;

import io.infosphere.bo.domain.Category;
import io.infosphere.bo.dto.CategoryDto;
import io.infosphere.bo.mapper.CategoryMapper;
import io.infosphere.bo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository repository;
    private CategoryMapper mapper;

    public List<CategoryDto> getAllCategories() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public CategoryDto create(CategoryDto categoryDto) {
        if(categoryExistsByName(categoryDto.getName())) {
            throw new IllegalArgumentException("Une categorie avec le meme nom existe deja");
        }
        return mapper.toDto(
                repository.save(mapper.toEntity(categoryDto))
        );
    }

    private boolean categoryExistsByName(String categoryName) {
        return repository.findAll().stream()
                .map(Category::getName)
                .anyMatch(name -> name.equalsIgnoreCase(categoryName));
    }
}
