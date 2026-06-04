package com.example1.demo1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("city") // 對應到資料表 city
public class City {

    /** 主鍵 ID，資料庫 auto increment */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /** 城市名稱 */
    @TableField("Name")
    private String name;

    /** 國家代碼（3 碼，如 USA / TWN） */
    @TableField("CountryCode")
    private String countryCode;

    /** 行政區 */
    @TableField("District")
    private String district;

    /** 人口數 */
    @TableField("Population")
    private Integer population;
}
