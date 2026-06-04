package com.example1.demo1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example1.demo1.commons.ApiResponse;
import com.example1.demo1.entity.City;
import com.example1.demo1.service.CityService;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/insert")
@RequiredArgsConstructor
public class insertCityController {

    private final CityService cityService;

    /**
     * 新增
     * 傳入 City，呼叫 BaseMapper 的 insert(T entity) 方法
     * @param city
     * @return
     */
    @PostMapping("/city")
    public ResponseEntity<ApiResponse<City>> insertCity(@RequestBody City city){

        City rtnCity = cityService.insertCity(city);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(rtnCity));

    }
    
}
