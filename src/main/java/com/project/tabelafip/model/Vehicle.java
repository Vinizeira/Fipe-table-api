package com.project.tabelafip.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Vehicle(
        @JsonAlias("TipoVeiculo") String vehicleType,
        @JsonAlias("Valor") String price,
        @JsonAlias("Marca") String brand,
        @JsonAlias("Modelo") String model,
        @JsonAlias("AnoModelo") String modelYear,
        @JsonAlias("Combustivel") String combustible,
        @JsonAlias("CodigoFipe") String fipeCode,
        @JsonAlias("MesReferencia") String referenceMonth,
        @JsonAlias("SiglaCombustivel") String combustibleAcronym
) {}
