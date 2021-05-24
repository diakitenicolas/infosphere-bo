package io.infosphere.bo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Getter@Setter
public class ShortArticleDto {

    private Long id;

    private String title;

    private boolean free;

    private String summary;

    private ZonedDateTime publicationDate;

    private boolean isSponsored;

    private Set<CategoryDto> categories;
}
