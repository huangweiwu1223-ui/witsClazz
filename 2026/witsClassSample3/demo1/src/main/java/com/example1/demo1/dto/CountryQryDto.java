package com.example1.demo1.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryQryDto {

    @JsonProperty("country_ids")
    private String countryIds;

    @JsonProperty("continentMap")
    private Map<String, Object> continentMap;
    
}
