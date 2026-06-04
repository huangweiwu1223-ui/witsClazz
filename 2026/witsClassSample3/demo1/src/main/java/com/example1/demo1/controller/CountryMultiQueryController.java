package com.example1.demo1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example1.demo1.commons.ApiResponse;
import com.example1.demo1.dto.CountryQryDto;
import com.example1.demo1.entity.Country;
import com.example1.demo1.service.CountryService;
import com.mysql.cj.util.StringUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/countryMultiQuery")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Country 查詢, 多筆 Controller", description = "查詢 Country 使用 IService 介面的範例 - 多筆 * 8 種方式")
public class CountryMultiQueryController {

    private final CountryService countryService;

    /**
     * 使用 內建的 查詢全部
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "查詢全部", description = "使用 內建的 查詢全部")
    public ResponseEntity<ApiResponse<List<Country>>> getAllCountries(){

        List<Country> rtnCountry =  countryService.list();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(rtnCountry));
    }

    /**
     * 傳入 國家代碼 的 字串(TWN,USA,JPN) 為條件查詢對應的紀錄
     * @param CountryQryDto
     * @return
     */
    @GetMapping("/listByIds")
    @Operation(summary = "依多筆 PK 值查詢", description = "傳入多筆 PK，每筆之間依逗號隔開")
    public ResponseEntity<ApiResponse<List<Country>>> getCountriesByIds(@RequestParam CountryQryDto countryQryDto){

        log.debug("countryIdsDto.getCountryIds:{}", countryQryDto.getCountryIds());

        List<Country> specificCountries = countryService.listByIds(StringUtils.split(countryQryDto.getCountryIds(), ",", false));

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(specificCountries));
    }

    /**
     * 查詢 By Map 條件
     * @param countryQryDto
     * @return
     */
    @GetMapping("/listByMap")
    @Operation(summary = "依 Map 查詢", description = "依 Map 的內容查詢資料")
    public ResponseEntity<ApiResponse<List<Country>>> getCountriesByMap(){

        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("continent", "Asia");

        List<Country> specificCountries = countryService.listByMap(filter);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(specificCountries));
    }

    /**
     * 查詢 GNP 大於 12000 的亞洲國家
     * @return
     */
    @GetMapping("/listByQueryWrapper")
    @Operation(summary = "使用 lambda 的 QueryWrappers 查資料, 返回 List<Country>", description = "建立一個針對 Country Entity 的 Lambda 查詢條件建構器, 返回一個 List<Country>")
    public ResponseEntity<ApiResponse<List<Country>>> getCountriesByQueryWrapper(){

        List<Country> specificCountries = countryService.list(Wrappers.<Country>lambdaQuery()
            .eq(Country::getContinent, "Asia")
            .gt(Country::getGnp, 12000.0)
        );

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(specificCountries));
    }

    /**
     * listMaps(Wrapper<T> queryWrapper) ：回傳 List<Map>，當你不想用 Entity 接收或要做多表聯查時好用
     * @return
     */
    @GetMapping("/listByQueryWrapperToListMap")
    @Operation(summary = "使用 lambda 的 QueryWrappers 查資料, 返回 List<Map>", description = "建立一個針對 Country Entity 的 Lambda 查詢條件建構器, 返回一個 List<Map>")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getCountriesByQueryWrapperToListMap(){

        List<Map<String, Object>> mapList = countryService.listMaps(Wrappers.<Country>lambdaQuery()
            .eq(Country::getContinent, "Africa")
            .gt(Country::getGnp, 12000.0)
        );

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(mapList));
    }

    /**
     * 查詢第一欄( 通常會是 PK 的欄位 )
     * @return
     */
    @GetMapping("/qryFirstField")
    @Operation(summary = "查詢第一個欄位的資料", description = "查詢第一欄( 通常會是 PK 的欄位 )")
    public ResponseEntity<ApiResponse<List<Object>>> getCountriesFirstField(){

        // listObjs() ：只查詢第一欄（通常是主鍵）
        List<Object> countryCodes = countryService.listObjs();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(countryCodes));
    }

    /**
     * 統計全表總筆數
     * @return
     */
    @GetMapping("/qryTotalCount")
    @Operation(summary = "統計所有資料筆數", description = "查詢 Country 資料表的所有筆數")
    public ResponseEntity<ApiResponse<Long>> getCountriesTotalCount(){

        // count() ：統計全表總筆數
        Long totalCount = countryService.count();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(totalCount));
    }

    /**
     * 依條件統計符合的筆數
     * @return
     */
    @GetMapping("/qryConditionMatchedCount")
    @Operation(summary = "統計 條件 的資料筆數", description = "依條件查詢 Country 資料表的資料筆數")
    public ResponseEntity<ApiResponse<Long>> getCountriesConditionMatchedCount(){

        // count(Wrapper<T> queryWrapper) ：依條件統計符合的筆數
        Long asiaCount = countryService.count(Wrappers.<Country>lambdaQuery()
            .eq(Country::getContinent, "Asia")
        );

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(asiaCount));
    }

    
}
