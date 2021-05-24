package io.infosphere.bo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionDto {

    private Long id;

    private int rank;

    private String imgUrl;

    private String title;

    private String content;
}
