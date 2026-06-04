package com.example1.demo1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("countryUpdate")
@RequiredArgsConstructor
@Tag(name = "更新 Country 紀錄", description = "更新 Country Table 的方法")
@Slf4j
public class CountryUpdateController {

    private final CountryService countryService;

    /**
     * 依傳入的 Country 更新資料
     * @param country
     * @return
     */
    @PutMapping("updateContryByCountryEntity")
    @Operation(summary = "傳入 Country Entity By PK 更新資料", description = "傳入 Country Entity By PK 更新資料")
    public ResponseEntity<ApiResponse<String>> updateContryByCountryEntity(@RequestBody Country country){

        boolean isSuccess = countryService.updateById(country);

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("更新成功") : ApiResponse.fail(-1, "更新失敗"));
    }

    /**
     * 傳入要更新的Country條件做更新
     * @param country
     * @return
     */
    @PutMapping("updateContryByLambdaQuery")
    @Operation(summary = "傳入要更新的Country條件做更新", description = "傳入要更新的Country條件做更新")
    public ResponseEntity<ApiResponse<String>> updateContryByLambdaQuery(@RequestBody Country country){

        boolean isSuccess = countryService.update(Wrappers.<Country>lambdaUpdate()
                .eq(Country::getCode, country.getCode())
                .set(Country::getGnp, country.getGnp()) // 直接指定欄位設值
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("更新成功") : ApiResponse.fail(-1, "更新失敗"));
    }


    
}
