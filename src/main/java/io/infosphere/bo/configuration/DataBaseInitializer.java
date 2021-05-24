package io.infosphere.bo.configuration;

import io.infosphere.bo.domain.Article;
import io.infosphere.bo.domain.Category;
import io.infosphere.bo.domain.Section;
import io.infosphere.bo.repository.ArticleRepository;
import io.infosphere.bo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.*;

@Component
public class DataBaseInitializer implements ApplicationRunner {

    private static final String SECTION_CONTENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sit amet ligula in mi commodo tempor vitae quis ligula. Aliquam convallis congue augue, nec fringilla leo finibus ac. Vestibulum pretium vitae sem et consectetur. In vel mollis leo. Suspendisse ornare enim in nisl mattis, at cursus arcu ultrices. Aliquam gravida maximus malesuada. Curabitur nec eleifend sem. Aliquam et ex non tellus rhoncus ullamcorper nec et dui. Curabitur pretium enim nisl, at imperdiet urna imperdiet quis. Duis bibendum lectus non mauris feugiat tristique. Sed et augue ac ex rhoncus maximus eu eget turpis. Integer eu egestas urna. Vivamus aliquet elit mollis vehicula suscipit.";

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        ZonedDateTime sevenMonthsAgo = ZonedDateTime.now().minusMonths(7);
        ZonedDateTime fiveMonthAgo =  ZonedDateTime.now().minusMonths(5);
        ZonedDateTime twoMonthsAgo =  ZonedDateTime.now().minusMonths(2);;
        ZonedDateTime twoWeeksAgo =  ZonedDateTime.now().minusDays(14);;

        Category sport = createAndSaveCategory("Sport");
        Category politic = createAndSaveCategory("Politique");
        Category economy = createAndSaveCategory("Economie");

        creatAndSaveArticle("Real 2eme de laLiga",Collections.singleton(sport), 1L, fiveMonthAgo);
        creatAndSaveArticle("Barca 3eme de laLiga", new HashSet<>(Arrays.asList(sport)), null, fiveMonthAgo);
        creatAndSaveArticle("Qui va gagner la LdC", new HashSet<>(Arrays.asList(sport)), null, twoMonthsAgo);
        creatAndSaveArticle("anniversaire guerre Salvador vs Honduras ", new HashSet<>(Arrays.asList(sport, politic)), null, twoWeeksAgo);
        creatAndSaveArticle("Debut de saison chaud pour Liverpool", Collections.singleton(sport), null, sevenMonthsAgo);

    }

    private Category createAndSaveCategory(String name) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(name + " description");
        return categoryRepository.save(category);
    }

    private Article creatAndSaveArticle(String title, Set<Category> categories, Long sponsorId, ZonedDateTime publicationDate){
        Article article = new Article();
        article.setTitle(title);
        article.setSummary("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sit amet ligula in mi commodo tempor vitae quis ligula. Aliquam convallis congue augue, nec fringilla leo finibus ac.");
        article.setCategories(categories);
        article.setSponsorId(sponsorId);
        article.setPublicationDate(publicationDate);
        Section section1 = createSection("section1", SECTION_CONTENT, "https://www.image.jpg", 1);
        Section section2 = createSection("section2", SECTION_CONTENT, "https://www.image.jpg", 2);
        Section section3 = createSection("section3", SECTION_CONTENT, "https://www.image.jpg", 3);
        article.setSections(Arrays.asList(section1, section2, section3));
        return articleRepository.save(article);
    }

    private Section createSection(String title, String content, String imgUrl, int rank) {
        Section section = new Section();
        section.setTitle(title);
        section.setContent(content);
        section.setImgUrl(imgUrl);
        section.setRank(rank);
        return section;
    }
}
