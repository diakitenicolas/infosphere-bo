package io.infosphere.bo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter@Setter
public class Article {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private String title;

  private boolean free;

  private String summary;

  private ZonedDateTime publicationDate;

  private Long sponsorId;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  //@OrderBy(Secti)
  @JoinColumn(name="article_id")
  private List<Section> sections;

//  @ElementCollection
//  private Set<String> tags;

  @ManyToMany
  @JoinTable(name="article_categories")
  private Set<Category> categories;
}

//filter : category,date start, date end,
