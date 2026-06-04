package com.example1.demo1.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example1.demo1.entity.Country;

@Mapper
public interface CountryMapper extends BaseMapper<Country> {

    void save(Country country);
    
}
