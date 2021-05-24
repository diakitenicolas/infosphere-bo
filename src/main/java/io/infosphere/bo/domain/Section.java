package io.infosphere.bo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Section {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private int rank;

  private String imgUrl;

  private String title;

  @Lob
  @Basic
  private String content;


}
