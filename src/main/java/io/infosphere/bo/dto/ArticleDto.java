package io.infosphere.bo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class ArticleDto extends ShortArticleDto {

    private List<SectionDto> sections;

}