package com.shuailee.study.mapstructdemo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package: com.shuailee.study.mapstructdemo
 * @description:
 * @author: klein
 * @date: 2021-05-12 10:24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    public String countryName;
    public String areaCode;
}
