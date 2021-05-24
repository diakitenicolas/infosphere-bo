package io.infosphere.bo.controllers;

import io.infosphere.bo.domain.Article;
import io.infosphere.bo.dto.ArticleDto;
import io.infosphere.bo.dto.ArticlePageDto;
import io.infosphere.bo.repository.ArticleRepository;
import io.infosphere.bo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.List;

@RestController
@RequestMapping("/v1/articles")
public class ArticleController {

    private static final String DEFAULT_MONTHS_AGO_LIMIT = "6";
    @Autowired
    private ArticleRepository repository;

    @Autowired
    private ArticleService service;

    @GetMapping(value = "/all")
    public List<Article> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Article create(@RequestBody Article article) {
        return repository.save(article);
    }

    @GetMapping(value = "/{id}")
    public ArticleDto getOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @GetMapping
    public ArticlePageDto getAllFiltering(
            @RequestParam int page,
            @RequestParam int offset,
            @RequestParam Long categoryId,
            @RequestParam(required = false, defaultValue = DEFAULT_MONTHS_AGO_LIMIT) Integer monthsAgoLimit) {

        return service.getAllByCategoryId(
                categoryId,
                page,
                offset,
                ZonedDateTime.now().minusMonths(monthsAgoLimit)
        );

    }
//
//    private ZonedDateTime getOldestPublicationDate(Integer monthsAgoLimit) {
//         int monthsAgoLimitOrDefault =   null == monthsAgoLimit
//                 ? DEFAULT_MONTHS_AGO_LIMIT
//                 : monthsAgoLimit;
//        return ZonedDateTime.now().minusMonths(monthsAgoLimitOrDefault);
//    }
}
