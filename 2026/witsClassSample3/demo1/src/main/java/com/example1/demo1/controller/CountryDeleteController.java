package com.example1.demo1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/countryDelete")
@RequiredArgsConstructor
@Tag(name="刪除 Country", description = "刪除 Country 的方法")
@Slf4j
public class CountryDeleteController {

    private final CountryService countryService;

    /**
     * 依傳入的 country pk 刪除資料
     * @param code
     * @return
     */
    @DeleteMapping("/countryDeleteById")
    @Operation(summary="依傳入的 pk 刪除 Country", description="依傳入的 country pk 刪除資料")
    public ResponseEntity<ApiResponse<String>> countryDeleteById(@RequestParam String code){

        boolean isSuccess = countryService.removeById(code);

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("刪除成功") : ApiResponse.fail(-1, "刪除失敗"));
    }

    /**
     * 傳入多筆 PK 做刪除, 彼此以逗號隔開
     * @param codes
     * @return
     */
    @DeleteMapping("/countryDeleteByIds")
    @Operation(summary="依傳入多筆的 pk 值刪除 Country 資料表", description="依傳入的 country 多筆 pk 值 刪除資料")
    public ResponseEntity<ApiResponse<String>> countryDeleteByIds(@RequestParam List<String> codeList){

        // boolean isSuccess = countryService.removeByIds(StringUtils.split(codes, ",", false));
        boolean isSuccess = countryService.removeByIds(codeList);

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("刪除成功") : ApiResponse.fail(-1, "刪除失敗"));
    }

    /**
     * 依傳入的 Map 刪除資料
     * @param delConditionMap
     * @return
     */
    @DeleteMapping("/countryDeleteByMap")
    @Operation(summary="依傳入的 Map 刪除 Country 資料表", description="依傳入的 Map 刪除資料")
    public ResponseEntity<ApiResponse<String>> countryDeleteByMap(@RequestParam Map<String,Object> delConditionMap){

        boolean isSuccess = countryService.removeByMap(delConditionMap);

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("刪除成功") : ApiResponse.fail(-1, "刪除失敗"));
    }

    /**
     * 依 LambdaQuery 條件刪除 Country 資料表
     * @param continent
     * @param population
     * @return
     */
    @DeleteMapping("/countryDeleteByLambdaQuery")
    @Operation(summary="依 LambdaQuery 條件刪除 Country 資料表", description="依 LambdaQuery 條件刪除 Country 資料表")
    public ResponseEntity<ApiResponse<String>> countryDeleteByLambdaQuery(@RequestParam String continent, Integer population){

        // 刪除 continent 為輸入的 continent、人口小於 傳入的 population
        boolean isSuccess = countryService.remove(Wrappers.<Country>lambdaQuery()
            .eq(Country::getContinent, continent)
            .lt(Country::getPopulation, population)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("刪除成功") : ApiResponse.fail(-1, "刪除失敗"));
    }
    
}
