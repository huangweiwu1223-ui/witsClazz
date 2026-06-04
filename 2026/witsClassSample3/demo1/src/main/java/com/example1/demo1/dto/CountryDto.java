package com.example1.demo1.dto;

import java.math.BigDecimal;

import com.example1.demo1.entity.ContinentEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("continent")
    // private String continent;
    private ContinentEnum continent;

    @JsonProperty("population")
    private Integer population;

    @JsonProperty("gnp")
    private BigDecimal gnp;
    
}
