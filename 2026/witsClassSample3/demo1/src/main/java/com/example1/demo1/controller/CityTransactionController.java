package com.example1.demo1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example1.demo1.commons.ApiResponse;
import com.example1.demo1.entity.City;
import com.example1.demo1.service.CityTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cityTransactionTest")
@Slf4j
@Tag(name="交易控制示範", description="使用 City 資料表做交易控制測試")

public class CityTransactionController {

    private final CityTransactionService cityTransactionService;

    /**
     * 一般交易控制
     * @return
     */
    @PostMapping("/cityTransactionTest1")
    @Operation(summary = "交易控制測試-1", description = "正常完成交易控制")
    public ResponseEntity<ApiResponse<String>> cityTransactionTest1(){

        City city1 = new City();
        city1.setId(4090);
        city1.setPopulation(12000);

        City city2 = new City();
        city2.setId(4099);
        city2.setName("TTES-AAA");

        cityTransactionService.doTransactionTest1(city1, city2);

        log.error("正常完成交易控制");


        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok("交易成功"));
    }

    /**
     * 由參數控制是否要拋錯
     * @param isThrowsException
     * @return
     */
    @PostMapping("/cityTransactionTest2")
    @Operation(summary = "交易控制測試-2", description = "由參數控制是否要中斷交易")
    public ResponseEntity<ApiResponse<String>> cityTransactionTest2(@RequestParam boolean isThrowsException){

        City city1 = new City();
        city1.setId(4090);
        city1.setPopulation(12000);

        City city2 = new City();
        city2.setId(4099);
        city2.setName("TTES-AAA");

        cityTransactionService.doTransactionTest2(city1, city2, isThrowsException);
        
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok("交易成功"));
    }

    /**
     * 加入 try-catch，使用 使用 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly() 中斷交易控制
     * @return
     */
    @PostMapping("/cityTransactionTest3")
    @Operation(summary = "交易控制測試-3", description = "使用 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly() 停止交易")
    public ResponseEntity<ApiResponse<String>> cityTransactionTest3(){

        City city1 = new City();
        city1.setId(4090);
        city1.setPopulation(12000);

        City city2 = new City();
        city2.setId(4099);
        city2.setName("TTES-AAA");
        try {
            cityTransactionService.doTransactionTest3(city1, city2);
            
        } catch (Exception e) {
            log.error("CityTransactionController.cityTransactionTest3 拋錯, {}", e.getMessage(), e);
        }


        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok("交易成功"));
    }
    
}
