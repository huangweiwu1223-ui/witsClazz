package com.example1.demo1.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example1.demo1.entity.Country;
import com.example1.demo1.mapper.CityMapper;
import com.example1.demo1.mapper.CountryMapper;
import com.example1.demo1.service.CityService;
import com.example1.demo1.service.CountryService;

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
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements CountryService {

    // private final CountryMapper countryMapper;

    // /**
    //  * save
    //  */
    // public void saveCountry(CountryDto countryDto){

    //     Country country = new Country();
    //     country.setCode(countryDto.getCode());
    //     country.setContinent(countryDto.getContinent());
    //     country.setGnp(countryDto.getGnp());
    //     country.setName(countryDto.getName());
    //     country.setPopulation(countryDto.getPopulation());

    //     countryMapper.save(country);
        
    // }


}
