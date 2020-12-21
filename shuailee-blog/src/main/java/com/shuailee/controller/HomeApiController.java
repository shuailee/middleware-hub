package com.shuailee.controller;

import com.shuailee.dao.ArticleMapper;
import com.shuailee.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/*接口开发*/
@RestController
public class HomeApiController {

    @Autowired
    ArticleMapper articleMapper;

    /**
    *  @RequestMapping()注解：用于定义一个请求映射，将请求映射到对应的控制器action上，可用于controller或action上。用于controller上，
         表示controller中的所有action都是以该地址作为父路径
         1 @RequestMapping(value="/get/{bookId}") bookId为变量，可以在action中动态指定；
         2 @RequestMapping(value="/get/id*")：正则匹配：可匹配“/get/idabc”或“/get/id”，但不匹配“/get/idabc/abc”;
         可以通过@PathVariable 注解提取路径中的变量(idPre,idNum)
    *  @PathVariable注解即提取路径中的变量值
    *
    * */

    //http://localhost:9081/queryarticle/1
    @RequestMapping(value = "/queryarticle/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Article queryArticle(@PathVariable int id) {
        return articleMapper.queryById(id);
    }
    /*
    * @RequestBody： 将HTTP请求正文json字符串转换为适合的HttpMessageConverter对象。
    * @Responsebody 表示该方法的返回结果直接写入HTTP response body中。一般在异步获取数据时使用，在使用@RequestMapping后，
    *   返回值通常解析为跳转路径，加上@Responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。
    *   比如异步获取json数据，加上@Responsebody后，会直接返回json数据
    *  跨域1
    *   origins为CrossOrigin的默认参数，即跨域来源，*即任何来源，也可以是其他域名，
    *       @CrossOrigin("http://test.com")
            @CrossOrigin(origins="http://test.com",maxAge=3600)
            跨域2
             <filter-mapping>
        <filter-name>CorsFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    * */
    @RequestMapping(value = "/addarticle",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public String addArticle(@RequestBody Article article) {
        //测试代码
        if(ObjectUtils.isEmpty(article)) {
            article.setArticle_title("入门吉他");
            article.setArticle_content("入门吉他入门吉他入门吉他入门吉他入门吉他入门吉他入门吉他入门吉他");
        }
        return articleMapper.add(article)== 1 ? "success" : "failed";
    }
}
