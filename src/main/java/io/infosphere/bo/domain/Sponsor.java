package io.infosphere.bo.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Sponsor {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
