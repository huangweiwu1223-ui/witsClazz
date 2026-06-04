package com.example1.demo1.service;

import java.util.List;

import com.example1.demo1.entity.City;

/**
 * City 業務邏輯介面。
 * <p>
 * 透過「介面 + 實作」分離，方便日後替換實作或寫單元測試 (mock)。
 * </p>
 */
public interface CityService {

    /** 列出所有城市 */
    List<City> listAll();

    /** 依國家代碼列出城市（呼叫 XML 中的 selectByCountryCode） */
    List<City> listByCountryCode(String code);

    // 新增
    City insertCity(City city);

    // 刪除 ById
    void deleteById(Integer id);

}
