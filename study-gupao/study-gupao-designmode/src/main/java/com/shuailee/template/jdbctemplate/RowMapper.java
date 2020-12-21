package com.shuailee.template.jdbctemplate;

import java.sql.ResultSet;

/**
 * @program: study-gupao
 * @description: ORM 逻辑的接口
 * @author: shuai.li
 * @create: 2019-05-30 19:20
 **/
public interface RowMapper<T> {
    T mapRow(ResultSet rs, int rowNum) throws Exception;
}
