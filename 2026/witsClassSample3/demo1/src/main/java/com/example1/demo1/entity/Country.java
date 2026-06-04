package com.example1.demo1.entity;

import java.io.Serial;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("country") // 對應到資料表 country
public class Country {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 國家代碼 (Primary Key)
     */
    @TableId(value = "Code", type = IdType.INPUT)
    private String code;

    /**
     * 國家名稱
     */
    @TableField("Name")
    private String name;

    /**
     * 洲別
     * Enum:
     * Asia
     * Europe
     * North America
     * Africa
     * Oceania
     * Antarctica
     * South America
     */
    @TableField("Continent")
    // private String continent;
    private ContinentEnum continent;

    /**
     * 區域
     */
    @TableField("Region")
    private String region;

    /**
     * 國土面積
     */
    @TableField("SurfaceArea")
    private BigDecimal surfaceArea;

    /**
     * 獨立年份
     */
    @TableField("IndepYear")
    private Short indepYear;

    /**
     * 人口數
     */
    @TableField("Population")
    private Integer population;

    /**
     * 平均壽命
     */
    @TableField("LifeExpectancy")
    private BigDecimal lifeExpectancy;

    /**
     * 國民生產毛額
     */
    @TableField("GNP")
    private BigDecimal gnp;

    /**
     * 舊版國民生產毛額
     */
    @TableField("GNPOld")
    private BigDecimal gnpOld;

    /**
     * 當地名稱
     */
    @TableField("LocalName")
    private String localName;

    /**
     * 政府體制
     */
    @TableField("GovernmentForm")
    private String governmentForm;

    /**
     * 國家元首
     */
    @TableField("HeadOfState")
    private String headOfState;

    /**
     * 首都城市ID
     */
    @TableField("Capital")
    private Integer capital;

    /**
     * 二碼國家代碼
     */
    @TableField("Code2")
    private String code2;
    
}
