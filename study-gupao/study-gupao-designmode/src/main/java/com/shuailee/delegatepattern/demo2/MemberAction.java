package com.shuailee.delegatepattern.demo2;

/**
 * @program: study-gupao
 * @description:模拟controller
 * @author: shuai.li
 * @create: 2019-05-13 20:06
 **/
public class MemberAction {
    /**
     *
     * 在springmvc 访问地址的url与Controller层配置的url是如何映射的,Controller层配置的url如何跟具体的方法映射的，参数又是如何绑定的
     *
     * url的地址可以根据request得到访问url地址，配置的url地址可以根据配置注解得到，这两者的url匹配上了说明映射成功了，
     * 除了url是不够的，还需要一个中间对象保存了url和method以及controller对象的信息，可以把这个中年的映射对象放入容器中，
     * 然后根据传入的url从容器取出进行匹配，取出来之后就可以根据映射来完成方法的调用了
     *
     * */
    public void getMemberById(String mid){

    }
}
