package io.infosphere.bo.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Getter@Setter
public class CategoryDto {

    private Long id;

    private String name;

    private String description;
}
