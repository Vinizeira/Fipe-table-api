package com.project.tabelafip.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record ModelList(
        @JsonAlias("modelos") List<Model> models
){}