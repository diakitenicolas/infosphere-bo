package io.infosphere.bo.repository;

import io.infosphere.bo.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    //Page<Article> findAllByCategoriesId(Long categoryId, Pageable pageable);
}
