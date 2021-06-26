package io.infosphere.bo.mapper;

import io.infosphere.bo.domain.Article;
import io.infosphere.bo.dto.ArticleDto;
import io.infosphere.bo.dto.ShortArticleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDto entityToDto(Article article);
    ShortArticleDto entityToShortDto(Article article);

    Article dtoToEntity(ArticleDto articleDto);
}