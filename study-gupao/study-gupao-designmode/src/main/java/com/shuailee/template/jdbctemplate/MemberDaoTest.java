package com.shuailee.template.jdbctemplate;

import java.util.List;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-05-31 11:43
 **/
public class MemberDaoTest {
    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao(null);
        List<?> result = memberDao.selectAll();
        System.out.println(result);
    }
}
