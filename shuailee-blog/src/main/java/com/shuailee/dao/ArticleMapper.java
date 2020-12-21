package com.shuailee.dao;

import com.shuailee.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: shuailee-blog
 * @description:
 * @author: shuai.li
 * @create: 2018-09-12 16:16
 **/
@Mapper
public interface ArticleMapper {
    @Select("SELECT * FROM article WHERE article_id = #{id}")
    Article queryById(@Param("id") int id);


    @Insert({"INSERT INTO article(article_title,article_content) VALUES(#{article_title},#{article_content})"})
    int add(Article user);
}
