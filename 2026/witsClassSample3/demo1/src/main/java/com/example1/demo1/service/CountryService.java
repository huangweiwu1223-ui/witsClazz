package com.example1.demo1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example1.demo1.entity.Country;

/**
 * Country 業務邏輯介面。
 * <p>
 * 透過「介面 + 實作」分離，方便日後替換實作或寫單元測試 (mock)。
 * </p>
 */
public interface CountryService extends IService<Country> {

    // public void saveCountry(CountryDto countryDto);


}
