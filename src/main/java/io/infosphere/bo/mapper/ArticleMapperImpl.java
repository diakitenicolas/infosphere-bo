package io.infosphere.bo.mapper;

import io.infosphere.bo.domain.*;
import io.infosphere.bo.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public ArticleDto entityToDto(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleDto articleDto = new ArticleDto();

        articleDto.setId( article.getId() );
        articleDto.setTitle( article.getTitle() );
        articleDto.setFree( article.isFree() );
        articleDto.setSummary( article.getSummary() );
        articleDto.setPublicationDate( article.getPublicationDate() );
        articleDto.setSections( sectionListToSectionDtoList( article.getSections() ) );
        articleDto.setCategories( categorySetToCategoryDtoSet( article.getCategories() ) );
        articleDto.setSponsored(article.getSponsorId() != null);

        return articleDto;
    }

    @Override
    public ShortArticleDto entityToShortDto(Article article) {
        if ( article == null ) {
            return null;
        }

        ShortArticleDto shortArticleDto = new ShortArticleDto();

        shortArticleDto.setId( article.getId() );
        shortArticleDto.setTitle( article.getTitle() );
        shortArticleDto.setFree( article.isFree() );
        shortArticleDto.setSummary( article.getSummary() );
        shortArticleDto.setPublicationDate( article.getPublicationDate() );
        shortArticleDto.setCategories( categorySetToCategoryDtoSet( article.getCategories() ) );
        shortArticleDto.setSponsored(article.getSponsorId() != null);

        return shortArticleDto;
    }

    protected SectionDto sectionToSectionDto(Section section) {
        if ( section == null ) {
            return null;
        }

        SectionDto sectionDto = new SectionDto();

        sectionDto.setId( section.getId() );
        sectionDto.setRank( section.getRank() );
        sectionDto.setImgUrl( section.getImgUrl() );
        sectionDto.setTitle( section.getTitle() );
        sectionDto.setContent( section.getContent() );

        return sectionDto;
    }

    protected List<SectionDto> sectionListToSectionDtoList(List<Section> list) {
        if ( list == null ) {
            return null;
        }

        List<SectionDto> list1 = new ArrayList<>( list.size() );
        for ( Section section : list ) {
            list1.add( sectionToSectionDto( section ) );
        }

        return list1;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );
        categoryDto.setDescription( category.getDescription() );

        return categoryDto;
    }

    protected Set<CategoryDto> categorySetToCategoryDtoSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDto> set1 = new HashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryToCategoryDto( category ) );
        }

        return set1;
    }
}