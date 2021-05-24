package io.infosphere.bo.mapper;

import io.infosphere.bo.domain.Article;
import io.infosphere.bo.dto.ArticleDto;
import io.infosphere.bo.dto.ShortArticleDto;
import org.mapstruct.Mapper;

//error when running app on intellij because for mapstruct version,
//TODO : Use mapstruct when upgrade mapstruct to version > 1.4.0 (java version to 11 too)
//@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDto entityToDto(Article article);
    ShortArticleDto entityToShortDto(Article article);

}