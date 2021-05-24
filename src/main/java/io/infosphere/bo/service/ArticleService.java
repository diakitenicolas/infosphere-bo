package io.infosphere.bo.service;

import io.infosphere.bo.domain.Article;
import io.infosphere.bo.domain.Category;
import io.infosphere.bo.dto.ArticleDto;
import io.infosphere.bo.dto.ArticlePageDto;
import io.infosphere.bo.mapper.ArticleMapper;
import io.infosphere.bo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleService(ArticleRepository repository, ArticleMapper articleMapper) {
        this.repository = repository;
        this.articleMapper = articleMapper;
    }

    public ArticleDto findOne(Long id) {
        return articleMapper.entityToDto(repository.getOne(id));
    }

    public ArticlePageDto getAllByCategoryId(Long categoryId, int page, int offset, ZonedDateTime oldestPublicationDate) {
        Page<Article> articlePage = repository.findAll(buildSpecificationOnCategoryId(categoryId, oldestPublicationDate),
                PageRequest.of(page, offset));

        return ArticlePageDto.builder()
                .currentPage(page)
                .offset(offset)
                .totalEntries(articlePage.getTotalElements())
                .hasNext(articlePage.hasNext())
                .hasPrevious(articlePage.hasPrevious())
                .totalPages(articlePage.getTotalPages())
                .content(articlePage.getContent().stream().map(articleMapper::entityToShortDto)
                        .collect(Collectors.toList()))
                .build();

    }

    private static Specification<Article> buildSpecificationOnCategoryId(Long categoryId, ZonedDateTime oldestPublicationDate) {
        return (Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Join<Article, Category> categories = root.join("categories");
            query.orderBy(cb.desc(root.get("sponsorId")), cb.desc(root.get("publicationDate")));

            return cb.and(
                    cb.equal(categories.get("id"), categoryId),
                    cb.greaterThanOrEqualTo(root.get("publicationDate"), oldestPublicationDate)
            );
//            return cb.equal(categories.get("id"), categoryId);
        };
    }

}
