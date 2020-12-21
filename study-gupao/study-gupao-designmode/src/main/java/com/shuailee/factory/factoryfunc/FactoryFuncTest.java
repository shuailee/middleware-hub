package com.shuailee.factory.factoryfunc;

import com.shuailee.factory.Milk;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2018-10-25 17:22
 **/
public class FactoryFuncTest {

    public static void main( String[] args )
    {
        //工厂方法模式
        //每个应用程序实例、业务实例都有自己的工厂，当加入一个业务实例例如三鹿的时候，需要添加对应的业务处理工厂

        /*应用场景：通常由应用程序直接使用new 创建新的对象，为了将对象的创建和使用相分离，采用工厂模
        式,即应用程序将对象的创建及初始化职责交给工厂对象。
        一般*/
        Factory factory=new MengniuFactoryImpl();//创建mengniu的工厂 必须知道品牌
        Milk milk=factory.getMilk();//生产mengniu的牛奶
        System.out.println(milk.getName());

    }
}
