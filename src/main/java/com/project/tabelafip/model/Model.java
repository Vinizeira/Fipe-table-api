package com.project.tabelafip.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Model(
        @JsonAlias("nome") String name,
        @JsonAlias("codigo") String code
){}
