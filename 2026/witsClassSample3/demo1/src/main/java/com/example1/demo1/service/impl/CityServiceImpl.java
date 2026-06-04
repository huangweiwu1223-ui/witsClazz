package com.example1.demo1.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example1.demo1.entity.City;
import com.example1.demo1.mapper.CityMapper;
import com.example1.demo1.service.CityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link CityService} 的實作。
 * <p>
 * 透過 MyBatis-Plus 的 {@link CityMapper} 完成資料庫存取。
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor // Lombok 會自動產生 constructor 注入 mapper
public class CityServiceImpl implements CityService {


    private final CityMapper cityMapper;

    @Override
    public List<City> listAll() {
        // selectList(null) 表示沒有 where 條件 = 查全部
        return cityMapper.selectList(null);
    }

    // @Override
    public List<City> listByCountryCode(String code) {
        // 呼叫 XML 中定義的方法
        return cityMapper.selectByCountryCode(code);
    }

    /**
     * City city
     * 直接呼叫 cityMapper 的 insert 方法，把 city 裡的資料寫入
     * 因 cityMapper 有繼承 BaseMapper，所以有基本的 insert 方法
     */
    public City insertCity(City city){
        log.debug("CityServiceImpl.insertCity execute.");
        cityMapper.insert(city);
        return city;
    }

    /**
     * 刪除 byId
     */
    public void deleteById(Integer id){

        log.debug("CityServiceImpl.deleteById execute.");
        cityMapper.deleteById(id);
    }

}
