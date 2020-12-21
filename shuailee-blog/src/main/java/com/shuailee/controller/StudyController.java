package com.shuailee.controller;


import com.shuailee.model.Greeting;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 1 @RestController注解规定了StudyController是一个Restful API的控制器，只返回API相关的数据，如果是普通的@Controller注解，如果在方法上不做特殊的配置，将默认返回一个视图
 * 2 @RequestMapping("/greeting")是一个请求映射,当浏览器访问 xxx://localhost:8080/home时，将会转入这个方法进行处理。@RequestMapping默认处理的是GET请求
 *      @RequestMapping(value = "/home", method = RequestMethod.GET),如果是POST请求，可以将method改为 RequestMethod.POST，也可以直接使用对应的maping注解：
 * - @GetMapping：Get请求，常用于页面访问操作
 * - @PostMapping：Post请求，常用于添加等表单操作
 * - @PutMapping：Put请求，常用于修改或编辑等表单操作
 * - @DeleteMapping：Delete操作，常用于删除操作
 *  3 @RequestParam用于获取该请求的参数，当浏览器访问 xxx://localhost:8080/home?name=Gaussic是，将会把参数的值写入name中，此处默认值是World，如果不设置默认值且不传递参数，将会报错
 *      针对https://my.oschina.net/gaussik/blog/781773的url参数Spring MVC提供了@PathVariable注解，来获取URL中的路径参数
 *      特别要注意的是，这个方法直接返回了一个Greeting对象，官方的解释是：Spring会使用Jackson JSON库自动地将这个Greeting对象转化为JSON并返回。
 *
 * */

@RestController
public class StudyController {
    private static final String template = "Hello, %s!";  // content 模板
    private final AtomicLong counter = new AtomicLong();  // 自动生成 id

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(
                counter.incrementAndGet(),    // 自增
                String.format(template, name) // content模板
        );
    }


    /**
     * 所有的参数用{}包含，再使用@PathVariable来获取,使用ModelMap来向模板页面传递数据
     * */
    @GetMapping("/{username}/blog/{blogId}")
    public String index(@PathVariable("username") String username,
                        @PathVariable("blogId") String blogId, ModelMap model) {
        model.addAttribute(username);
        model.addAttribute(blogId);
        return "index";
    }
}


