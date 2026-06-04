package com.example1.demo1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example1.demo1.commons.ApiResponse;
import com.example1.demo1.service.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/delete")
@RequiredArgsConstructor
public class deleteCityController {

    private final CityService cityService;

    /**
     * 摻除 ById
     * @param id
     * @return
     */
    @DeleteMapping("/delById")
    public ResponseEntity<ApiResponse<String>> deleteById(@RequestParam(required = true) Integer id){

        cityService.deleteById(id);

        StringBuilder sbMsg = new StringBuilder("刪除 ").append(id).append(" 成功");

        return ResponseEntity.ok(ApiResponse.ok(sbMsg.toString()));

    }
    
}
