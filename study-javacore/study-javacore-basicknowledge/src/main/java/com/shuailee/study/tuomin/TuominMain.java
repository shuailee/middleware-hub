package com.shuailee.study.tuomin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @package: com.shuailee.study.tuomin
 * @description:
 * @author: klein
 * @date: 2021-05-12 20:31
 **/
public class TuominMain {
    public static void main(String[] args) throws JsonProcessingException {
        UserResponse userResponse = new UserResponse(11L, "code", "kelin", "13111111111", "nmn", 11, "nnnn", "x11111111");
        ObjectMapper objectMapper = new ObjectMapper();
        String val = objectMapper.writeValueAsString(userResponse);
        System.out.println(val);
    }
}
