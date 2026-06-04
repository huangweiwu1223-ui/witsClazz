package com.example1.demo1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example1.demo1.entity.City;

@Mapper
public interface CityMapper extends BaseMapper<City> {

    // @Select("select * from city where country=#{code}")
    public List<City> selectByCountryCode(String code);
    
}
