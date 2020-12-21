package com.shuailee.controller;

import com.shuailee.model.Article;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*页面开发*/
@Controller
public class HomeController {
    @RequestMapping("/index")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public String index() {
        return "index";
    }

    /*
    *  FreeMarke渲染页面
    * */
    @RequestMapping("/articles")
    public String articles(Map<String, Object> map){
        map.put("name", "Joe");
        map.put("sex", 1);
        // 模拟数据
        List<Map<String, Object>> friends = new ArrayList<Map<String, Object>>();
        Map<String, Object> friend = new HashMap<String, Object>();
        friend.put("name", "xbq");
        friend.put("age", 22);
        friends.add(friend);
        friend = new HashMap<String, Object>();
        friend.put("name", "July");
        friend.put("age", 18);
        friends.add(friend);
        map.put("friends", friends);
        return "articles";
    }

    @RequestMapping("/welcome")
    public String welcome(Map<String, Object> map){
        map.put("message", "hello wold");
        return "welcome";
    }




}
