package com.example1.demo1.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example1.demo1.commons.ApiResponse;
import com.example1.demo1.entity.City;
import com.example1.demo1.mapper.CityMapper;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cityChainQuery")
@Slf4j
@Tag(name="條件構造器操作", description="使用 MyBatis-plus 的 條件構造器")
public class CityChainQueryController {

    private final CityMapper cityMapper;

    /**
     * 組合試煉接串接查資料
     {
        "id": 3900,
        "name": "Joliet",
        "countryCode": "USA",
        "district": "I",
        "population": 100000
     }
     * @param city
     * @return
     */
    @GetMapping("/queryWrapper1")
    @Operation(summary = "組合試煉接串接查資料", description = "使用 eq、likeRight、gt、ge 查資料")
    public ResponseEntity<ApiResponse<List<City>>> queryWrapper1( City city){

        LambdaQueryWrapper<City> cityWrapper = new LambdaQueryWrapper<>();

        cityWrapper.eq(City::getCountryCode, city.getCountryCode())
            .eq(StringUtils.isNotBlank(city.getName()), City::getName, city.getName())
            .likeRight(City::getDistrict, city.getDistrict())
            .gt(City::getPopulation, city.getPopulation())
            .ge(City::getId, city.getId())
        ;
       
        log.info("cityWrapper:{}", Objects.toString(cityWrapper));
        List<City> rstCity = cityMapper.selectList(cityWrapper);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(rstCity));
    }

    /**
     * id 介於 3950~4000，有6筆
     * @param idFrom
     * @param idTo
     * @return
     */
    @GetMapping("/queryWrapperBetween")
    @Operation(summary = "組合試煉接串接查資料", description = "使用 between 查資料")
    public ResponseEntity<ApiResponse<List<City>>> queryWrapperBetween(@RequestParam Integer idFrom, @RequestParam Integer idTo){

        LambdaQueryWrapper<City> cityWrapper = new LambdaQueryWrapper<>();

        cityWrapper.between(City::getId, idFrom, idTo)
        ;
       
        log.info("cityWrapper:{}", Objects.toString(cityWrapper));
        List<City> rstCity = cityMapper.selectList(cityWrapper);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(rstCity));
    }

    /**
     * 組合試煉接串接查資料-使用 inSQL 查資料
     * region:Southern Europe
     * @param idFrom
     * @param idTo
     * @return
     */
    @GetMapping("/queryWrapperInsQL")
    @Operation(summary = "組合試煉接串接查資料", description = "使用 inSQL 查資料")
    public ResponseEntity<ApiResponse<List<City>>> queryWrapperInsQL(@RequestParam String region){

        LambdaQueryWrapper<City> cityWrapper = new LambdaQueryWrapper<>();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select code from world.country where Region = '").append(region).append("'");

        cityWrapper.inSql(City::getCountryCode, sqlBuilder.toString());
       
        List<City> rstCity = cityMapper.selectList(cityWrapper);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(rstCity));
    }

    /**
     * 組合試煉接串接查資料-group by and order by desc
     * group By countryCode
     * order by countryCode desc
     * @return
     */
    @GetMapping("/queryWrapperGroupByOrderBy")
    @Operation(summary = "組合試煉接串接查資料", description = "使用 GroupBy 分組 + orderBy desc 排序 查資料")
    public ResponseEntity<ApiResponse<List<City>>> queryWrapperGroupByOrderBy(){

        // QueryWrapper<City> qryWrapper = new QueryWrapper<>();

        // qryWrapper.select("countryCode")
        //     .ge("population", 1000000)
        //     .groupBy("countrycode")
        //     .orderByDesc("countrycode");
       
        // List<City> rstCity = cityMapper.selectList(qryWrapper);

        LambdaQueryWrapper<City> cityWrapper = new LambdaQueryWrapper<>();

        cityWrapper.select(City::getCountryCode)
            .ge(City::getPopulation, 1000000)
            .groupBy(City::getCountryCode)
            .orderByDesc(City::getCountryCode)
            ;

        List<City> rstCity = cityMapper.selectList(cityWrapper);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(rstCity));
    }

    /**
     * 由 SQL 翻為 查詢建構器
		select id, name, countrycode, district, population 
		from city 
		where name like 'K%'
		and countrycode in (select code from country where continent = 'Asia')
		and Population >= 550000
		and id between 1000 and 2000
		and district like '%er%'
     * @return
     */
    @GetMapping("/queryWrapper2")
    @Operation(summary = "由SQL翻為 LambdaQueryWrapper", description = "由SQL翻為 LambdaQueryWrapper")
    public ResponseEntity<ApiResponse<List<City>>> queryWrapper2(){

        LambdaQueryWrapper<City> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        StringBuilder sbSQL = new StringBuilder();
        sbSQL.append("select Code from world.country where continent = 'Asia'");

        lambdaQueryWrapper
            .select(City::getId, City::getName, City::getCountryCode, City::getDistrict, City::getPopulation)
            .likeRight(City::getName, "K")
            .inSql(City::getCountryCode, sbSQL.toString())
            .ge(City::getPopulation, 550000)
            .between(City::getId, 1000, 2000)
            .like(City::getDistrict, "er")
            ;

            List<City> rstCity = cityMapper.selectList(lambdaQueryWrapper);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(rstCity));
    } 
}
