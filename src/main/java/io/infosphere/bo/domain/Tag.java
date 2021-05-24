package io.infosphere.bo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter@Setter
public class Tag {

    @Id
    private Long id;

    @NaturalId
    private String name;

    private String description;
}
