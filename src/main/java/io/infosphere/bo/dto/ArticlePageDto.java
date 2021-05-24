package io.infosphere.bo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@Builder
public class ArticlePageDto {

    private int totalPages;

    private int currentPage;

    private int offset;

    private long totalEntries;

    private boolean hasNext;

    private boolean hasPrevious;

    private List<ShortArticleDto> content;
}
