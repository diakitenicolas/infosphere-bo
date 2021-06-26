package io.infosphere.bo.service;

import io.infosphere.bo.domain.Article;
import io.infosphere.bo.domain.Category;
import io.infosphere.bo.dto.ArticleDto;
import io.infosphere.bo.dto.ArticlePageDto;
import io.infosphere.bo.dto.SectionDto;
import io.infosphere.bo.mapper.ArticleMapper;
import io.infosphere.bo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

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

    public ArticleDto create (ArticleDto articleDto) {
        checkArticleValidity(articleDto);
        Article article = articleMapper.dtoToEntity(articleDto);
        return articleMapper.entityToDto(repository.save(article));
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

    private void checkArticleValidity(ArticleDto articleDto) {
        if(!StringUtils.hasText(articleDto.getTitle())) {
            throw new IllegalArgumentException("Le titre de l'article ne doit pas etre vide");
        }

        if(CollectionUtils.isEmpty(articleDto.getCategories())){
            throw new IllegalArgumentException("L'article doit avoir au moins une categorie");
        }

        if(CollectionUtils.isEmpty(articleDto.getSections())){
            throw new IllegalArgumentException("L'article doit avoir au moins une section");
        }

        if(articleDto.getPublicationDate() != null && articleDto.getPublicationDate().isBefore(ZonedDateTime.now())) {
            throw new IllegalArgumentException("La date de publication de l'article doit etre dans le future ");
        }

        articleDto.getSections().stream().map(SectionDto::getContent)
                .filter(not(StringUtils::hasText))
                .findAny()
                .ifPresent(s -> {throw new IllegalArgumentException("Toutes les sections doivent avoir un contenu");});
    }

}
