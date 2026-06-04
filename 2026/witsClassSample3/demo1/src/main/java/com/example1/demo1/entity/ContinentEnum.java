package com.example1.demo1.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

@Getter
public enum ContinentEnum {

    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    AFRICA("Africa"),
    OCEANIA("Oceania"),
    ANTARCTICA("Antarctica"),
    SOUTH_AMERICA("South America");

    @EnumValue
    private final String value;

    ContinentEnum(String value) {
        this.value = value;
    }
}