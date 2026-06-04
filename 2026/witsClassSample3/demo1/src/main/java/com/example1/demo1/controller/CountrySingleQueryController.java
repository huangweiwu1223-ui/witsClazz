package com.example1.demo1.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example1.demo1.commons.ApiResponse;
import com.example1.demo1.entity.Country;
import com.example1.demo1.service.CountryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/countrySingleQuery")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Country 查詢, 單筆 Controller", description = "查詢 Country 使用 IService 介面的範例 - 單筆 * 4")
public class CountrySingleQueryController {

    private final CountryService countryService;

    /**
     * 依主鍵查詢
     * @return
     */
    @GetMapping("/getCountryById")
    @Operation(summary = "依主鍵查詢", description = "依主鍵查詢")
    public ResponseEntity<ApiResponse<Country>> getCountryById(){

        // getById(Serializable id) ：依主鍵查詢
        Country country = countryService.getById("TWN");

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(country));
    }

    /**
     * 依條件查詢單筆
     * @param CountryQryDto
     * @return
     */
    @GetMapping("/getCountryByCondition")
    @Operation(summary = "依條件查詢單筆", description = "依條件查詢單筆")
    public ResponseEntity<ApiResponse<Country>> getCountryByCondition(){

        // getOne(Wrapper<T> queryWrapper) ：依條件查詢單筆
        // 注意：如果查出多筆會拋出異常！
        Country country = countryService.getOne(Wrappers.<Country>lambdaQuery()
            .eq(Country::getName, "Japan")
        );

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(country));
    }

    /**
     * 查詢 By Map 條件
     * @param countryQryDto
     * @return
     */
    @GetMapping("/getCountryFirstByConditionToEntity")
    @Operation(summary = "按條件只查出第一筆", description = "第二個參數傳 false，查出多筆時不報錯，只取第一筆")
    public ResponseEntity<ApiResponse<Country>> getCountryFirstByConditionToEntity(){

        // getOne(Wrapper<T> queryWrapper, boolean throwEx) ：第二個參數傳 false，查出多筆時不報錯，只取第一筆
        Country firstCountry = countryService.getOne(Wrappers.<Country>lambdaQuery()
            .eq(Country::getContinent, "Asia"), false);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(firstCountry));
    }

    /**
     * 查詢 GNP 大於 12000 的亞洲國家
     * @return
     */
    @GetMapping("/getCountryByConditionToMap")
    @Operation(summary = "查出單筆並轉成 Map 格式", description = "查出單筆並轉成 Map 格式")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getCountryByConditionToMap(){

        // getMap(Wrapper<T> queryWrapper) ：查出單筆並轉成 Map 格式
        Map<String, Object> countryMap = countryService.getMap(Wrappers.<Country>lambdaQuery().eq(Country::getCode, "USA"));

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(countryMap));
    }





    
}
