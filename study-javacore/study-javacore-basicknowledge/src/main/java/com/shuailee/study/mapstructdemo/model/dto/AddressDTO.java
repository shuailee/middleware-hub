package com.shuailee.study.mapstructdemo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package: com.shuailee.study.mapstructdemo
 * @description:
 * @author: klein
 * @date: 2021-05-12 10:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    public String area;
    public String city;
    public CountryDTO country;


}
