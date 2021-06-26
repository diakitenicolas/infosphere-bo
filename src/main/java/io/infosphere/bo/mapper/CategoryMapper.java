package io.infosphere.bo.mapper;

import io.infosphere.bo.domain.Category;
import io.infosphere.bo.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
