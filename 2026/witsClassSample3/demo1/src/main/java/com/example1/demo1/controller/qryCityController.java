package com.example1.demo1.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example1.demo1.commons.ApiResponse;
import com.example1.demo1.entity.City;
import com.example1.demo1.service.CityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/query")
@RequiredArgsConstructor
@Tag(name="qryController", description = "查詢City的Controller")
public class qryCityController {

    private final CityService cityService;

    @GetMapping("")
    @Operation(summary = "查詢City的資料", description = "可查全部或傳入country查單一地區")
    public ResponseEntity<ApiResponse<List<City>>> list(
            @RequestParam(value = "country", required = false) String country) {
        // 有帶 country 就走 mapper.xml 的方法；沒帶就全查
        List<City> data;
        if (StringUtils.isBlank(country)){
            data = cityService.listAll();
        } else {
            data = cityService.listByCountryCode(country);

        }
        
        // List<City> data = (StringUtils.isBlank(country))
        //         ? cityService.listAll()
        //         : cityService.listByCountryCode(country);
        return ResponseEntity.ok(ApiResponse.ok(data));
    }
    
}
