package com.shuailee.study.basicknowledge.yanma;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @package: com.shuailee.study.basicknowledge.yanma
 * @description:
 * @author: klein
 * @date: 2021-05-11 19:32
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;

    @VirtualMobile(conditionField = "cryptoStatus")
    private String mobile;

    private Integer cryptoStatus;

    private LocalDateTime createTime;

}
