package com.example1.demo1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example1.demo1.commons.ApiResponse;
import com.example1.demo1.entity.Country;
import com.example1.demo1.service.CountryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/countryInsert")
@RequiredArgsConstructor
@Tag(name="Country 新增資料 Controller", description = "單筆 Country 或批量的 Country 寫入 Country 資料表")
public class CountryInsertController {

    private final CountryService countryService;

    /**
     * 傳入 Country 的 Entity 寫回 Country 資料表
     * @param country
     * @return
     */
    @PostMapping("/countrySingleInsert")
    @Operation(summary="一個 Country 寫入 table", description="傳入 Country 寫入 Country Table")
    public ResponseEntity<ApiResponse<String>> countrySingleInsert(@RequestBody Country country){

        boolean isSuccess = countryService.save(country);

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("寫入成功") : ApiResponse.fail(-1, "寫入失敗"));
    }

    /**
     * 傳入 List<Country> 批量寫入 Table
     * @param countryList
     * @return
     */
    @PostMapping("/countryBatchInsert")
    @Operation(summary="多個 Country 寫入 table", description="傳入 List<Country> 批次寫入 Country Table, 預設 1000 筆一批")
    public ResponseEntity<ApiResponse<String>> countryBatchInsert(@RequestBody List<Country> countryList){

        boolean isSuccess = countryService.saveBatch(countryList);

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("寫入成功") : ApiResponse.fail(-1, "寫入失敗"));
    }

    /**
     * 傳入 List<Country> 與 batchSize 控制批次寫入 Country 資料表的量
     * @param countryList
     * @param batchSize
     * @return
     */
    @PostMapping("/countryBatchInsertByCnt")
    @Operation(summary="多個 Country 寫入 table", description="傳入 List<Country> 批次寫入 Country Table, 一批的量由參數傳入")
    public ResponseEntity<ApiResponse<String>> countryBatchInsertByBatchSize(@RequestBody List<Country> countryList, @RequestParam Integer batchSize){

        boolean isSuccess = countryService.saveBatch(countryList, batchSize);

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("寫入成功") : ApiResponse.fail(-1, "寫入失敗"));
    }

    /**
     * 傳入 Country, 若不存在則新增, 存在則更新
     * @param country
     * @return
     */
    @PostMapping("/countrySaveOrUpdate")
    @Operation(summary="多個 Country 寫入 table", description="傳入 Country, 若不存在則新增, 存在則更新")
    public ResponseEntity<ApiResponse<String>> countrySaveOrUpdate(@RequestBody Country country){

        boolean isSuccess = countryService.saveOrUpdate(country);

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("寫入成功") : ApiResponse.fail(-1, "寫入失敗"));
    }

    /**
     * 傳入 List<Country> 批量處理, 若不存在則新增, 存在則更新
     * @param country
     * @return
     */
    @PostMapping("/countrySaveOrUpdateBatch")
    @Operation(summary="多個 Country 寫入 table", description="傳入 Country, 若不存在則新增, 存在則更新")
    public ResponseEntity<ApiResponse<String>> countrySaveOrUpdateBatch(@RequestBody List<Country> countryList){

        boolean isSuccess = countryService.saveOrUpdateBatch(countryList);

        // 批次更新, 自訂一批的量
        // boolean isSuccess = countryService.saveOrUpdateBatch(countryList, 20);

        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess? ApiResponse.ok("寫入成功") : ApiResponse.fail(-1, "寫入失敗"));
    }
    
}
