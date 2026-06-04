package com.example1.demo1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example1.demo1.commons.ApiResponse;
import com.example1.demo1.entity.Country;
import com.example1.demo1.service.CountryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/countryChainAction")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Country 鍊式調用 Controller", description = "鍊式調用, 使用 lambdaQuery 與 lambdaUpdate")
public class CountryChainQueryController {

    private final CountryService countryService;
    
    /**
     * 鏈式查詢：查詢亞洲、人口大於 1 億、依 GNP 倒序的前幾筆國家
     * @return
     */
    @GetMapping("getChainConditionQuery")
    @Operation(summary="getChainConditionQuery", description = "鍊式調用-查詢, 使用 lambdaQuery()")
    public ResponseEntity<ApiResponse<List<Country>>> getChainConditionQuery(){
        
        // 🌟 鏈式查詢：查詢亞洲、人口大於 1 億、依 GNP 倒序的前幾筆國家
        List<Country> result = countryService.lambdaQuery()
            .eq(Country::getContinent, "Asia")
            .gt(Country::getPopulation, 100000000)
            .orderByDesc(Country::getGnp)
            .list(); // 最後直接點出 .list()、.one() 或 .count()

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(result));

    }

    /**
     * 鏈式更新：把 Code 為 "TWN" 的國家人口修改為 傳入的 數字
     * @param population
     * @return
     */
    @GetMapping("setChainConditionUpdate")
    @Operation(summary="setChainConditionUpdate", description = "鍊式調用-更新, 使用 lambdaUpdate()")
    public ResponseEntity<ApiResponse<String >> setChainConditionUpdate(@RequestParam Integer population){

        // 🌟 鏈式更新：把 Code 為 "TWN" 的國家人口修改為 23500000
        // 原為 22256000
        boolean updateSuccess = countryService.lambdaUpdate()
                .eq(Country::getCode, "TWN")
                .set(Country::getPopulation, population)
                .update(); // 最後直接點出 .update() 執行

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(updateSuccess? "更新成功" : "更新失敗" ));

    }

    
}
